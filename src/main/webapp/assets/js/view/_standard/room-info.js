var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/standard/roominfo',
            data: caller.searchView.getData(),
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
        var saveList = [].concat(caller.gridView01.getData());
        saveList = saveList.concat(caller.gridView01.getData('deleted'));

        axboot.ajax({
            type: 'PUT',
            url: '/api/v1/standard/roominfo',
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
    PAGE_DELETE: function (caller, act, data) {
        var items = caller.gridView01.getData('selected');
        var ids = items.map(function (value) {
            return value.id;
        });
        axboot.ajax({
            type: 'DELETE',
            url: '/api/v1/standard/roominfo/' + ids.join(','),
            callback: function (res) {
                axToast.push('삭제 되었습니다');
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
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

            excel: function () {},
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
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
        this.filter = $('#filter');

        this.roomTypCd = $('.js-roomTypCd').on('change', function () {
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
    },

    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),

            roomTypCd: this.roomTypCd.val(),
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
            showRowSelector: true,
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
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
                // ACTIONS.dispatch(ACTIONS.PAGE_DELETE);
            },
        });
    },
    // getData: function (_type) {
    //     var list = [];
    //     var _list = this.target.getList(_type);
    //     console.log('getData', _list);
    //     if (_type == 'modified' || _type == 'deleted') {
    //         list = ax5.util.filter(_list, function () {
    //             // delete this.deleted;
    //             return this.key;
    //         });
    //     } else {
    //         list = _list;
    //     }
    //     return list;
    // },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});
