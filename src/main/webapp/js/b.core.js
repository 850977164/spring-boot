var B = {
    ui: {
        doSearch: function (frm) {
            var $frm = $(frm);
            var searchStr = $("input[name='searchStr']", $frm).val();
            if (searchStr == "") {
                return false;
            }
            var url = $o.attr("action");
            window.location = url + "?searchStr=" + searchStr;
        }
    },
    keyCode: {
        ENTER: 13, ESC: 27, END: 35, HOME: 36,
        SHIFT: 16, TAB: 9,
        LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
        DELETE: 46, BACKSPACE: 8
    },
    eventType: {
        pageClear: "pageClear",	// 用于重新ajaxLoad、关闭nabTab, 关闭dialog时，去除xheditor等需要特殊处理的资源
        resizeGrid: "resizeGrid"	// 用于窗口或dialog大小调整
    },
    isOverAxis: function (x, reference, size) {
        //Determines when x coordinate is over "b" element axis
        return (x > reference) && (x < (reference + size));
    },
    isOver: function (y, x, top, left, height, width) {
        //Determines when x, y coordinates is over "b" element
        return this.isOverAxis(y, top, height) && this.isOverAxis(x, left, width);
    },

    pageInfo: {
        pageNum: "pageNum",
        numPerPage: "numPerPage",
        orderField: "orderField",
        orderDirection: "orderDirection"
    },
    statusCode: {ok: 200, error: 300, timeout: 301},
    ui: {sbar: true},
    frag: {}, //page fragment
    _msg: {}, //alert message
    _set: {
        loginUrl: "", //session timeout
        loginTitle: "", //if loginTitle open a login dialog
        debug: false
    },
    jsonEval: function (data) {
        try {
            if ($.type(data) == 'string')
                return eval('(' + data + ')');
            else return data;
        } catch (e) {
            return {};
        }
    },
    msg: function (key, args) {
        var _format = function (str, args) {
            args = args || [];
            var result = str || "";
            for (var i = 0; i < args.length; i++) {
                result = result.replace(new RegExp("\\{" + i + "\\}", "g"), args[i]);
            }
            return result;
        }
        return _format(this._msg[key], args);
    },
    debug: function (msg) {
        if (this._set.debug) {
            if (typeof(console) != "undefined") console.log(msg);
            else alert(msg);
        }
    },
    ajaxError: function (xhr, ajaxOptions, thrownError) {
        B.debug("系统忙，请稍后重试");
        //alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
    },
    ajaxDone: function (json) {
        if (json.statusCode == B.statusCode.error) {
        } else if (json.statusCode == B.statusCode.timeout) {
        } else {
        }
        ;
    }

};
(function ($) {
    setTimeout(function () {
        initUI();
    }, 500);

    $.fn.extend({
        /**
         * @param {Object} op: {type:GET/POST, url:ajax请求地址, data:ajax请求参数列表, callback:回调函数 }
         */
        ajaxUrl: function (op) {
            var $this = $(this);
            $.ajax({
                type: op.type || 'GET',
                url: op.url,
                data: op.data,
                cache: false,
                success: function (response) {
                    var json = B.jsonEval(response);
                    if (json.statusCode == B.statusCode.error) {
                        if (json.message) console.log(json.message);
                    } else {
                        $this.html(response).initUI();
                        if ($.isFunction(op.callback)) op.callback(response);
                    }

                    if (json.statusCode == B.statusCode.timeout) {
                        alert("timeout");
                    }

                },
                error: B.ajaxError,
                statusCode: {
                    503: function (xhr, ajaxOptions, thrownError) {
                    }
                }
            });
        },
        loadUrl: function (url, data, callback) {
            $(this).ajaxUrl({url: url, data: data, callback: callback});
        },
        initUI: function () {
            return this.each(function () {
                if ($.isFunction(initUI)) initUI(this);
            });
        }
    });

    /**
     * 扩展String方法
     */
    $.extend(String.prototype, {
        isPositiveInteger: function () {
            return (new RegExp(/^[1-9]\d*$/).test(this));
        },
        isInteger: function () {
            return (new RegExp(/^\d+$/).test(this));
        },
        isNumber: function (value, element) {
            return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
        },
        trim: function () {
            return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
        },
        startsWith: function (pattern) {
            return this.indexOf(pattern) === 0;
        },
        endsWith: function (pattern) {
            var d = this.length - pattern.length;
            return d >= 0 && this.lastIndexOf(pattern) === d;
        },
        replaceSuffix: function (index) {
            return this.replace(/\[[0-9]+\]/, '[' + index + ']').replace('#index#', index);
        },
        trans: function () {
            return this.replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"');
        },
        encodeTXT: function () {
            return (this).replaceAll('&', '&amp;').replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;");
        },
        replaceAll: function (os, ns) {
            return this.replace(new RegExp(os, "gm"), ns);
        },
        replaceTm: function ($data) {
            if (!$data) return this;
            return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})", "g"), function ($1) {
                return $data[$1.replace(/[{}]+/g, "")];
            });
        },
        replaceTmById: function (_box) {
            var $parent = _box || $(document);
            return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})", "g"), function ($1) {
                var $input = $parent.find("#" + $1.replace(/[{}]+/g, ""));
                return $input.val() ? $input.val() : $1;
            });
        },
        replaceUrlP: function (v) {
            return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})", "g"), function ($1) {
                return v ? v : $1;
            });
        },
        isFinishedTm: function () {
            return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this));
        },
        skipChar: function (ch) {
            if (!this || this.length === 0) {
                return '';
            }
            if (this.charAt(0) === ch) {
                return this.substring(1).skipChar(ch);
            }
            return this;
        },
        isValidPwd: function () {
            return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this));
        },
        isValidMail: function () {
            return (new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
        },
        isSpaces: function () {
            for (var i = 0; i < this.length; i += 1) {
                var ch = this.charAt(i);
                if (ch != ' ' && ch != "\n" && ch != "\t" && ch != "\r") {
                    return false;
                }
            }
            return true;
        },
        isPhone: function () {
            return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
        },
        isUrl: function () {
            return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
        },
        isExternalUrl: function () {
            return this.isUrl() && this.indexOf("://" + document.domain) == -1;
        }
    });

})(jQuery);


function initUI(_box) {
    var $p = $(_box || document);
    $(".hash-abridge", $p).each(function () {
        $(this).hover(
            function () {
                var $this = $(this);
                var rel = $this.attr("rel");
                var block = $this.attr("block");
                var type = $this.data("type");
                var txhash = $this.attr("rel");
                var createTime = $this.data("time");
                if ("hash" == type) {
                    $this.append($("<span class='hash-complete'><a href='block/" + block + "'>" + rel + "</a></span>"));
                } else if ("txhash" == type) {
                    $this.append($("<span class='hash-complete'><a href='tx/list/" + block + "/" + txhash + "/" + createTime + "'>" + rel + "</a></span>"));
                }
            },
            function () {
                var $this = $(this);
                $this.find("span:last").remove();
            }
        );
    });
    $(".pagination", $p).each(function () {
        var $this = $(this);
        var pageForm = $this.find("form");
        $(this).find("li>a").click(function (event) {
            var pageStart = 1;
            var $a = $(this);
            if ($a.attr("pNext")) {
                pageStart = $a.attr("pNext");
            } else {
                pageStart = $a.html();
            }
            $("input[name='page.start']", pageForm).val(pageStart);
            alert(pageForm.serializeArray());
            var rel = pageForm.attr("rel");
            //提交form 表单
            var $rel = $("#" + rel);
            $rel.loadUrl(pageForm.attr("action"), pageForm.serializeArray(), function () {
            });
            alert(pageForm.attr("action") + '---' + pageForm.serializeArray());
            event.preventDefault();
        });
    });
    //展开显示或隐藏更多的资产详情
    $(".J-assetTerm", $p).each(function () {
        var $this = $(this);
        $(this).click(function () {
            var $i = $this.children('.iconfont');
            var $number = $this.parents('.valid-time').children('.number');
            var $term = $this.parents('.valid-time').children('.term');
            if ($number.hasClass('fold')) {
                $number.removeClass('fold');
                $term.removeClass('fold');
                $i.removeClass('icon-xia').addClass('icon-shang');
            } else {
                $number.addClass('fold');
                $term.addClass('fold');
                $i.removeClass('icon-shang').addClass('icon-xia');
            }
        });
    });
    $('.footer', $p).each(function () {
        if ($('.footer').offset().top + 120 < $(window).height()) {
            $('.footer').css('margin-top', $(window).height() - ($('.footer').offset().top + 120));
        }
    });

};

$(function () {
    $(".J-showCode").click(function () {
        var $this = $(this);
        var $code = $this.parents('.trade-cnt').children('.code');
        var $icon = $this.children('.iconfont');
        if ($code.hasClass('fold')) {
            $code.removeClass('fold');
            $icon.removeClass('icon-xia').addClass('icon-shang');
        } else {
            $code.addClass('fold');
            $icon.removeClass('icon-shang').addClass('icon-xia');
        }
        console.log("浏览器可视区域高度：" + $(window).height());
    });
    /*$(window).scroll(function(){
     if($(window).scrollTop() + $(window).height() >= $(document).height() - 120){
     var obj = '<div class="trade-cnt">\
     <h3 class="tit3">创建账户</h3>\
     <ul class="info-group info-group-striped">\
     <li>\
     <label class="item-name">账户地址：</label>\
     <span class="item-info">bubiV8i7RHkroSy3y7JaAMkxh6VEqZDBMCnGQNdY</span>\
     </li>\
     <span class="btn show-code J-showCode">浏览代码 <i class="iconfont icon-xia"></i></span>\
     </ul>\
     <div class="code fold"></div>\
     </div>';

     $('.loading-circle').css('display', 'block').before(obj);
     }
     });*/

    //底栏
    if ($('.footer').offset().top + $('.footer').height() < $(window).height()) {
        $('.footer').css('margin-top', $(window).height() - $('.footer').offset().top - $('.footer').height());
    }
});