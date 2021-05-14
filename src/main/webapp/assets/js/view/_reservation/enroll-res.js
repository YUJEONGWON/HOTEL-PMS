var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    //투숙메모 search
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);

        axboot.ajax({
            type: 'GET',
            url: '/api/v1/standard/roomInfo',
            data: paramObj,
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

    MODAL_OPEN: function (caller, act, data) {
        // var item = caller.searchView.getData();

        // if (!item.guestNm) {
        //     axDialog.alert('이름은 필수입니다.');
        //     return false;
        // }
        // axboot.ajax({
        //     type: 'GET',
        //     url: '/api/v1/standar/guestInfo',
        //     data: JSON.stringify(item),
        //     callback: function (res) {
        //         caller.formView01.setData(res);
        //     },
        // });

        if (!data) data = {};

        axboot.modal.open({
            width: 780,
            height: 450,
            iframe: {
                param: 'id=' + (data.id || ''),
                url: 'enroll-res-modal.jsp',
            },
            header: { title: '모달등록' },
            callback: function (data) {
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
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
    this.searchView.initView();
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
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        // this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');

        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');

        axboot.buttonClick(this, 'data-search-view-01-btn', {
            search: function () {
                // ACTIONS.dispatch(ACTIONS.MODAL_OPEN,this.item);ACTIONS.dispatch(ACTIONS.MODAL_OPEN,this.item);
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN);
            },
        });
    },
    getData: function () {
        return {
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val(),
        };
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
