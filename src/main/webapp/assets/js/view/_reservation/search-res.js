var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/chk',
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
            url: '/api/v1/chk',
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
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
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
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
                { key: 'id', label: '아이디', width: 100, align: 'center', editor: 'text' },
                { key: 'sno', label: '일련번호', width: 100, align: 'center', editor: 'text' },
                { key: 'guestId', label: '투숙객아이디', width: 100, align: 'center', editor: 'text' },
                { key: 'adultCnt', label: '성인수', width: 100, align: 'center', editor: 'text' },
                { key: 'chldCnt', label: '아동수', width: 100, align: 'center', editor: 'text' },
                { key: 'advnYn', label: '선수금여부', width: 100, align: 'left', editor: 'text' },
                { key: 'rsvNum', label: '예약번호', width: 100, align: 'left', editor: 'text' },
                { key: 'rsvDt', label: '예약일', width: 100, align: 'left', editor: 'text' },
                { key: 'guestNm', label: '투숙객', width: 100, align: 'left', editor: 'text' },
                {
                    key: 'roomTypCd',
                    label: '객실타입',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_ROOM_TYPE'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_ROOM_TYPE'].map[this.value];
                    },
                },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center', editor: 'text' },
                { key: 'arrDt', label: '도착일', width: 100, align: 'center', editor: 'text' },
                { key: 'depDt', label: '출발일', width: 100, align: 'center', editor: 'text' },
                {
                    key: 'srcCd',
                    label: '예약경로',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_RESERVATION_ROUTE'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_RESERVATION_ROUTE'].map[this.value];
                    },
                },
                {
                    key: 'saleTypCd',
                    label: '판매유형',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_SALE_TYPE'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_SALE_TYPE'].map[this.value];
                    },
                },
                {
                    key: 'sttusCd',
                    label: '상태',
                    width: 100,
                    align: 'center',
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['PMS_STAY_STATUS'],
                        },
                    },
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_STAY_STATUS'].map[this.value];
                    },
                },
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
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                delete this.deleted;
                return this.key;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});
