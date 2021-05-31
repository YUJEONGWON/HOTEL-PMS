var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    //투숙메모 search
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/standard/roominfo',
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var item = caller.formView01.getData();
        if (!item.id) item.__created__ = true;
        axboot.ajax({
            type: 'POST',
            url: '/api/v1/chk/',
            data: JSON.stringify(item),
            callback: function (res) {
                axToast.push('저장 되었습니다');
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
        });
    },

    MODAL_OPEN: function (caller, act, data) {
        if (!data) data = {};

        axboot.modal.open({
            width: 780,
            height: 450,
            iframe: {
                param: 'guestNm=' + (data.guestNm || '') + '&guestTel=' + (data.guestTel || '') + '&email=' + (data.email || ''),
                url: 'enroll-res-modal.jsp',
            },
            header: { title: '모달등록' },
            callback: function (data) {
                if (data) {
                    caller.formView01.setGuest(data);
                }
                this.close();
            },
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.formView01.initView();
    this.gridView01.initView();
    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    setGuest: function (data) {
        this.model.set('guestNm', data.guestNm || '');
        this.model.set('guestNmEng', data.guestNmEng || '');
        this.model.set('guestTel', data.guestTel || '');
        this.model.set('email', data.email || '');
        this.model.set('langCd', data.langCd || '');
        this.model.set('brth', data.brth || '');
        this.model.set('gender', data.gender || '');
    },
    validate: function () {
        var item = this.model.get();

        var rs = this.model.validate();

        if (rs.error) {
            alert(rs.error[0].jquery.attr('title') + '을(를) 입력해주세요.');
            rs.error[0].jquery.focus();
            return false;
        }

        return true;
    },
    getSearchData: function () {
        return {
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val(),
        };
    },
    initEvent: function () {
        var _this = this;
        axboot.buttonClick(this, 'data-form-view-01-btn', {
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.FORM_CLEAR);
            },
        });

        this.arrDt.on('change', function () {
            var arrDt = $(this).val();
            var depDt = _this.depDt.val();
            if (!arrDt || !depDt) return;
            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('depDt', momArrDt.add(nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.depDt.on('change', function () {
            var arrDt = _this.arrDt.val();
            var depDt = $(this).val();
            if (!arrDt || !depDt) return;
            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('arrDt', momDepDt.add(-nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.nightCnt.on('change', function () {
            var arrDt = _this.arrDt.val();
            if (!arrDt) return;
            var nightCnt = _this.nightCnt.val();
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('nightCnt', nightCnt);
            }
            _this.model.set('depDt', moment(arrDt).add(nightCnt, 'days').format('yyyy-MM-DD'));
        });
    },
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        // this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');

        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');

        axboot.buttonClick(this, 'data-search-view-01-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN, {
                    guestNm: this.guestNm.val(),
                    guestTel: this.guestTel.val(),
                    email: this.email.val(),
                });
            },
        });

        function createPicker(target) {
            return new ax5.ui.picker().bind(
                (pickerDefault = {
                    target: target,
                    direction: 'top',
                    content: {
                        width: 270,
                        margin: 10,
                        type: 'date',
                        config: {
                            lang: {
                                yearTmpl: '%s년',
                                months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                                dayTmpl: '%s',
                            },
                        },
                        formatter: {
                            pattern: 'date',
                        },
                    },
                    onStateChanged: function () {
                        if (this.state == 'open') {
                            //console.log(this.item);
                            var selectedValue = this.self.getContentValue(this.item['$target']);
                            if (!selectedValue) {
                                this.item.pickerCalendar[0].ax5uiInstance.setSelection([ax5.util.date(new Date(), { add: { d: 1 } })]);
                            }
                        }
                    },
                })
            );
        }

        createPicker($('[data-ax5picker="arrDt"]'));
        createPicker($('[data-ax5picker="depDt"]'));

        this.arrDt = $('[data-ax-path="arrDt"]');
        this.depDt = $('[data-ax-path="depDt"]');
        this.nightCnt = $('[data-ax-path="nightCnt"]');

        this.initEvent();
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: false,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'roomNum', label: '객실번호', width: 100, align: 'left', editor: 'text' },
                { key: 'roomTypCd', label: '객실타입', width: 100, align: 'left', editor: 'text' },
                { key: 'dndYn', label: 'DND 여부', width: 100, align: 'left', editor: 'text' },
                { key: 'ebYn', label: 'ExBed 여부', width: 100, align: 'center', editor: 'text' },
                { key: 'roomSttusCd', label: '객실상태', width: 100, align: 'center', editor: 'text' },
                { key: 'clnSttusCd', label: '청소상태', width: 100, align: 'center', editor: 'text' },
                { key: 'svcSttusCd', label: '서비스상태', width: 100, align: 'center', editor: 'text' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            delete: function () {
                //ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
        });
    },
});
