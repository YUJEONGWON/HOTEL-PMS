<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/_standard/guest-info.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>


        <div role="page-header">
            <ax:form name="searchView0" >
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:tr >
                            <ax:td label="이름" width="30%">
                                <input type="text" name="guestNm" data-ax-path="companyNm" class="form-control" />
                            </ax:td>
                            <ax:td label="전화번호" width="30%">
                                <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control"  />
                            </ax:td>
                            <ax:td label="이메일" width="40%">
                                <input type="text" name="email" data-ax-path="email" class="form-control"  />
                            </ax:td>
                        </ax:tr>
                        

                        <ax:tr >
                            <ax:td label="투숙날짜" width="60%">
                                    <button class="btn btn-default" data-grid-control="row-add">오늘</button>
                                    <button class="btn btn-default" data-grid-control="row-add">어제</button>
                                    <button class="btn btn-default" data-grid-control="row-add">3일</button>
                                    <button class="btn btn-default" data-grid-control="row-add">7일</button>
                                    <button class="btn btn-default" data-grid-control="row-add">1개월</button>
                                    <button class="btn btn-default" data-grid-control="row-add">3개월</button>
                                    <button class="btn btn-default" data-grid-control="row-add">6개월</button>
                                    <button class="btn btn-default" data-grid-control="row-add">1년</button>                                    
                            </ax:td>
                            <ax:td width="*">
                                <div class="" data-ax5picker="date">
                                    <input type="text" class="form-control W100" style="display:inline-block;"  placeholder="yyyy/mm/dd">
                                    ~
                                    <input type="text" class="form-control W100" style="display:inline-block;"  placeholder="yyyy/mm/dd">
                                                              
                                 </div>
                            </ax:td>
                               
                        </ax:tr>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="40%" style="padding-right: 10px;">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i> 투숙객 목록</h2>
                    </div>
                    <div class="right">
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;overflow: scroll;"></div>
            </ax:split-panel>
            <ax:splitter></ax:splitter>
            
            <ax:split-panel width="*" style="padding-left: 10px;">
                <ax:split-layout name="ax2" orientation="horizontal">
                    <ax:split-panel width="*" height="50%" >
                        <div data-fit-height-aside="form-view-01">
                            <div class="ax-button-group">
                                <div class="left">
                                    <h2><i class="cqc-news"></i> 투숙객 정보 </h2>
                                </div>

                            </div>

                            <form name="form"  style="overflow: auto;" class="js-form">
                                <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                                    <ax:tr labelWidth="120px">
                                        <ax:td label="이름" width="50%">
                                            <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control" readonly="readonly">
                                        </ax:td>
                                        <ax:td label="영문" width="50%">
                                            <ax:common-code groupCd="guestNmEng" dataPath="guestNmEng" />
                                        </ax:td>
                                    </ax:tr>

                                    <ax:tr labelWidth="120px">
                                        <ax:td label="연락처" width="50%">
                                            <input type="text" name="guestTel" data-ax-path="guestTel" title="회사명" class="form-control" data-ax-validate="required" />
                                        </ax:td>
                                        <ax:td label="이메일" width="50%">
                                            <input type="text" name="email" data-ax-path="email" class="form-control"  />
                                        </ax:td>
                                    </ax:tr>

                                    <ax:tr labelWidth="120px">
                                        <ax:td label="언어" width="50%">
                                            <ax:common-code groupCd="PMS_LANG" clazz="js-langCd" emptyText="전체"/>
                                        </ax:td>
                                        <ax:td label="전화번호" width="50%">
                                            <input type="date" data-ax-path="brth" style="display:inline-block;" class="form-control W100" name="brth" >
                                            <input type="radio" name="gender" data-ax-path="gender" value="남"> 남
                                            <input type="radio" name="gender" data-ax-path="gender" value="여"> 여
                                        </ax:td>
                                    </ax:tr>

                                    <ax:tr labelWidth="120px">
                                        <ax:td label="비고" width="100%">
                                            <textarea name="rmk" data-ax-path="rmk" rows="5" class="form-control"></textarea>
                                        </ax:td>
                                    </ax:tr>

                                </ax:tbl>
                            </form>
                        </div>
                    </ax:split-panel>
                    
                    
                    <ax:split-panel width="*" height="*">
                        <div class="ax-button-group" data-fit-height-aside="grid-view-02">
                            <div class="left">
                                <h2><i class="cqc-list"></i> 투숙 이력</h2>
                            </div>
                            <div class="right">
                            </div>
                        </div>
                        <div data-ax5grid="grid-view-02" data-fit-height-content="grid-view-02" style="height: 300px;"></div>
                    </ax:split-panel>
                </ax:split-layout>
            </ax:split-panel>
         
        </ax:split-layout>

    </jsp:body>
</ax:layout>