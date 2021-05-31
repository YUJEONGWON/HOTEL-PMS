<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("guestNm", requestUtils.getString("guestNm"));
    request.setAttribute("guestTel", requestUtils.getString("guestTel"));
    request.setAttribute("email", requestUtils.getString("email"));
%>
<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>
<ax:set key="axbody_class" value="baseStyle"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <script>
            var modalParams = {guestNm: "${guestNm}",guestTel: "${guestTel}",email: "${email}"};
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/_reservation/enroll-res-modal.js' />"></script>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h2 class="title">
            <i class="cqc-browser"></i>
            투숙객목록
        </h2>
    </jsp:attribute>
    <jsp:body>


        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" height="50%"  style="padding-right: 0px;">

            
                <div data-ax-tr>
                    <div data-ax-td style="width:100%">                                          
                            <div data-ax5grid="grid-view-01" onsubmit="return false" data-fit-height-content="grid-view-01" style="height: 300px;"></div>                                     
                    </div>>
                </div>
    

            </ax:split-panel>
            
            <ax:split-panel width="*"  height="50%" style="padding-right: 0px;">
            
                <form name="form" class="js-form" onsubmit="return false;">
                    <div data-ax-tbl class="ax-form-tbl">

                        <div data-ax-tr>
                            <div data-ax-td style="width:40%">
                                <div data-ax-td-label style="width:120px;">이름</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control">
                                </div>
                            </div>
                            <div data-ax-td style="width:60%">
                                <div data-ax-td-label style="width:120px;">영문</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control" >
                                
                                </div>
                            </div>
                        </div>

                        <div data-ax-tr>
                            <div data-ax-td style="width:40%">
                                <div data-ax-td-label style="width:120px;">연락처</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestTel" data-ax-path="guestTel"  class="form-control"/>
                                </div>
                            </div>
                            <div data-ax-td style="width:60%">
                                <div data-ax-td-label style="width:120px;">이메일</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="email" data-ax-path="email" class="form-control" />
                                </div>
                            </div>
                        </div>

                        <div data-ax-tr>
                            <div data-ax-td style="width:40%">
                                <div data-ax-td-label style="width:120px;">언어</div>
                                <div data-ax-td-wrap>
                                    <ax:common-code groupCd="PMS_LANG" name="langCd" clazz="js-langCd"  emptyText="전체"/>
                                </div>
                            </div>
                            <div data-ax-td style="width:60%">
                                <div data-ax-td-label style="width:120px;">생년월일</div>
                                <div data-ax-td-wrap>
                                    <input type="date" style="display:inline-block;"  data-ax-path="brth" class="form-control W100" name="brth" >
                                    <input type="radio" name="gender" data-ax-path="gender" value="남"> 남
                                    <input type="radio" name="gender" data-ax-path="gender" value="여"> 여
                                </div>
                            </div>
                        </div>
                        
                        <div data-ax-tr>
                            <div data-ax-td >
                                <div data-ax-td-label style="width:120px;">비고</div>
                                <div data-ax-td-wrap>
                                    <textarea name="rmk" data-ax-path="rmk" rows="5" style="width:100%"class="form-control"></textarea>
                                       
                                </div>
                            </div>
                            
                        </div>
                      

                    </div>
                </form>
            </ax:split-panel>
        </ax:split-layout>
        
        <ax:page-buttons>
            <button type="button" class="btn btn-default" data-page-btn="close"> 닫기 </button>
            <button type="button" class="btn btn-info" data-page-btn="choice"> 선택 </button>
        </ax:page-buttons>
    </jsp:body>
</ax:layout>