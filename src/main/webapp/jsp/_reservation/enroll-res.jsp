<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/_reservation/enroll-res.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>

        <form name="form" class="js-form" style="overflow: auto;" onsubmit="return false;">
            <div data-ax-tbl class="ax-form-tbl">

                <div data-ax-tr>
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">도착일</div>
                        <div data-ax-td-wrap>
                            <input type="date" data-ax-path="arrDt" class="form-control" name="arrDt" value="2018-07-22" min="2018-01-01" max="2018-12-31">
                        </div>
                    </div>
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">숙박수</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="id" data-ax-path="id" class="form-control" >
                        </div>
                    </div>
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">출발일</div>
                        <div data-ax-td-wrap>
                            <input type="date" data-ax-path="depDt" class="form-control" name="depDt" value="2018-07-22" min="2018-01-01" max="2018-12-31">
                        </div>
                    </div>
                </div>

                <div data-ax-tr>
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">객실타입</div>
                        <div data-ax-td-wrap>
                            <ax:common-code groupCd="PMS_ROOM_TYPE" clazz="js-roomTypCd" emptyText="전체"/>
                        </div>
                    </div>
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">성인수</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="adultCnt" data-ax-path="adultCnt" class="form-control" />
                        </div>
                    </div>
                    
                    <div data-ax-td style="width:33%">
                        <div data-ax-td-label style="width:120px;">아동수</div>
                        <div data-ax-td-wrap>
                            <input type="text" name="chldCnt" data-ax-path="chldCnt" class="form-control" />
                        </div>
                    </div>
                </div>
                <ax:form name="searchView0">
                    <div data-ax-tr>
                        
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;" >
                                <div tr style="">투숙객</div>                
                                <div tr style=""> <button type="button" class="btn btn-default" data-search-view-01-btn="search">
                                <i class="cqc-circle-with-plus"></i> 검색 </button></div> 
                            </div> 
                            
                            <div wrap>
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">이름</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="guestNm" data-ax-path="guestNm"  class="js-guestNm form-control" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">영문</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control" />
                                        </div>
                                    </div>
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">연락처</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="guestTel" data-ax-path="guestTel"  class="js-guestTel form-control" value=""/>
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">이메일</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="email" data-ax-path="email" class="js-email form-control" value=""/>
                                        </div>
                                    </div>
                                </div>
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">언어</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_LANG" clazz="js-roomTypCd" emptyText="전체"/>
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px;">생년월일</div>
                                        <div data-ax-td-wrap>
                                            <input type="date" style="display:inline-block;"  data-ax-path="brth" class="form-control W100" name="brth" >
                                            <input type="radio" name="gender" data-ax-path="gender" value="남"> 남
                                            <input type="radio" name="gender" data-ax-path="gender" value="여"> 여
                                        </div>
                                    </div>
                                </div>
                                
                                
                            </div>
                        </div>
                        
                    </div>
                </ax:form>
                
                <div data-ax-tr>
                    <div data-ax-td style="width:100%">
                        <div data-ax-td-label style="width:120px;">판매/결제</div>
                        <div wrap>
                            <div data-ax-tr>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">판매유형</div>
                                    <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_SALE_TYPE" clazz="js-roomTypCd" emptyText="전체"/>
                                   </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">예약경로</div>
                                    <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_RESERVATION_ROUTE" clazz="js-roomTypCd" emptyText="전체"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div data-ax-tr>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">결제방법</div>
                                    <div data-ax-td-wrap>
                                        <ax:common-code groupCd="PMS_PAY_METHOD" clazz="js-roomTypCd" emptyText="전체"/>
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">선수금 여부</div>
                                    
                                        <input type="checkbox" name="ceo" data-ax-path="ceo"/>
                                    
                                </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">결제금액</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="ceo" data-ax-path="ceo" class="form-control" />
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:120px;">서비스금액</div>
                                    <div data-ax-td-wrap>
                                        <input type="text" name="ceo" data-ax-path="ceo" class="form-control" />
                                    </div>
                                </div>
                            </div>
                            
                            
                        </div>
                    </div>
                    
                </div>
                
                

                <div data-ax-tr>
                    <div data-ax-td style="width:100%">
                        <div data-ax-td-label style="width:120px;">투숙메모</div>                                       
                        <div data-ax-td-wrap>
                            <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                <div class="left">
                                    <h2><i class="cqc-list"></i>
                                        투숙 메모 </h2>
                                </div>
                                <div class="right">
                                    <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                                    <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-plus"></i> 삭제</button>
                                </div>
                            </div>
                            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
                        
                        </div>
                    </div>>
                </div>

                

            </div>
        </form>



    </jsp:body>
</ax:layout>