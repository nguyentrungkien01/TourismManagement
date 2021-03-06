<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="vnt-content">
    <div class="vnt-main-top">
        <div id="vnt-navation" class="breadcrumb">
            <div class="wrapper">
                <div class="navation">
                    <ul>
                        <li><a title="Trang chủ" href="<c:url value='/' />"><span>Trang
                                    chủ</span></a>
                        </li>
                        <li><a href="/tin-tuc" title="Tin tức"><span>Tour du lịch</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="wrapCont">
        <div class="wrapper">
            <div class="tourTitle">
                <h3 id="tourTitle"></h3>
            </div>
            <div class="mod-content row">
                <div id="vnt-main" class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                    <div id="vnt-slide-thumbnail"></div>
                    <div class="boxTour" id="flag1">
                        <div class="title"><span class="fa-info-circle">Điểm nhấn hành trình</span></div>
                        <div class="content" id="tourContentDetail"></div>
                    </div>
                    <div class="boxTour" id="flag2">
                        <div class="title"><span class="fa-map">Lịch trình</span></div>
                        <div class="content">
                            <div class="listDay" id="scheduleList"></div>
                        </div>
                    </div>
                    <div class="boxTour" id="flag3">
                        <div class="title service-more" style="cursor:pointer"><span class="fa-paperclip">Dịch vụ bao
                                gồm và không bao gồm (Xem
                                Thêm)</span></div>
                        <div class="content service-more-content ml-5" style="display: none;">
                            <div class="the-content desc" id="serviceList"></div>
                        </div>
                    </div>
                    <div class="boxTour" id="flag4">
                        <div class="title note-more" style="cursor:pointer"><span class="fa-note-sticky">Ghi
                                chú (Xem Thêm)</span></div>
                        <div class="content note-more-content" style="display: none;">
                            <div class="the-content desc ml-5" id="noteList"></div>
                        </div>
                    </div>
                    <div class="boxTour" id="flag5">
                        <div class="title"><span class="fa-calendar-check">Ngày khởi hành khác</span></div>
                        <div class="content">
                            <div id="mda-table-price">
                                <div
                                    class="mda-list-price mda-tap ative table-responsive mCustomScrollbar _mCS_1 mCS_no_scrollbar">
                                    <div id="mCSB_1" class="mCustomScrollBox mCS-light mCSB_horizontal mCSB_inside">
                                        <div id="mCSB_1_container"
                                            class="mCSB_container mCS_x_hidden mCS_no_scrollbar_x">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>STT</th>
                                                        <th style="white-space: nowrap;">
                                                            Ngày khởi hành</th>
                                                        <th>Đặc điểm</th>
                                                        <th width="90px">Số Chỗ</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="dptDetailList"></tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="flagEnd"></div>
                </div>
                <div id="vnt-sidebar" class="col-lg-4 col-md-4 col-sm-12 col-xs-12 hidden-sm hidden-xs">
                    <div class="boxDesign1">
                        <div class="name" id="generalTourTitle"></div>
                        <div class="attr">
                            <ul id="generalTourInfo">
                            </ul>
                        </div>
                    </div>
                    <div class="mnfixed_wrap">
                        <div class="mnfixed_self mnfixed_fixed_fixed"
                            style="width: 376.667px; z-index: 101; position: initial; top: initial; left: initial; bottom: initial;">
                            <div class="boxFix">
                                <div class="boxPrice">
                                    <div class="price">
                                        <div class="txt">Giá từ:</div>
                                        <div class="red" id="giactt"></div>
                                    </div>
                                    <div class="attr">
                                        <p style="margin:0in 0in 0.0001pt"><strong><span style="font-size: 1rem;">Điểm
                                                    Nổi Bật Chương Trình:</span></strong></p>
                                        <ul>
                                            <li>Tặng 1 lần test covid tại Thổ Nhĩ Kỳ.</li>
                                            <li>Khách sạn 5 sao cả hành trình</li>
                                            <li>Trải nghiệm 1 đêm khách sạn Hang đá ở Cappadocia</li>
                                            <li>Tham quan nhà thờ hồi giáo Blue Mosque.</li>
                                            <li>Tham quan thành cổ Troy.</li>
                                            <li>Tham quan thành cổ La Mã Pergamon.</li>
                                            <li>Thưởng thức rượu Thổ và show múa bụng.</li>
                                            <li>Dạo thuyền quanh eo biển Bosphorus.</li>
                                            <li>Bảo hiểm Covid mức 50,000USD</li>
                                        </ul>
                                    </div>
                                    <div class="bot">
                                        <div class="btn-booking dt">
                                            <input type="hidden" name="tourid" id="tourid" value="16128">
                                            <input type="hidden" name="con" id="con" value="10">
                                            <input type="hidden" name="hasvisa" id="hasvisa" value="">
                                            <button type="button" class="btn-order-tour" id="tourBookingBtn">Đặt
                                                tour</button> </div>
                                    </div>
                                </div>
                                <div class="boxDesign2">
                                    <ul>
                                        <li class="active">
                                            <a href="#flag1">
                                                <span class="fa-info-circle">Điểm nhấn hành trình</span>
                                            </a>
                                        </li>
                                        <li class="">
                                            <a href="#flag2">
                                                <span class="fa-map">Lịch trình</span>
                                            </a>
                                        </li>
                                        <li class="">
                                            <a href="#flag3">
                                                <span class="fa-paperclip">Dịch vụ bao gồm và không bao gồm</span>
                                            </a>
                                        </li>
                                        <li class="">
                                            <a href="#flag4">
                                                <span class="fa-sticky-note">Ghi chú</span>
                                            </a>
                                        </li>
                                        <li class="">
                                            <a href="#flag5">
                                                <span class="fa-calendar-check">Ngày khởi hành khác</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="p-3">
                                    <span class="heading">Đánh giá người dùng</span>
                                    <div id="userRating"></div>
                                    <hr>

                                    <div class="row">
                                        <div class="side">
                                            <div>5 <span class="fa fa-star checked"></span></div>
                                        </div>
                                        <div class="middle">
                                            <div class="bar-container">
                                                <div class="bar-5"></div>
                                            </div>
                                        </div>
                                        <div class="side right">
                                            <div id="5a"></div>
                                        </div>
                                        <div class="side">
                                            <div>4 <span class="fa fa-star checked"></span></div>
                                        </div>
                                        <div class="middle">
                                            <div class="bar-container">
                                                <div class="bar-4"></div>
                                            </div>
                                        </div>
                                        <div class="side right">
                                            <div id="4a"></div>
                                        </div>
                                        <div class="side">
                                            <div>3 <span class="fa fa-star checked"></span></div>
                                        </div>
                                        <div class="middle">
                                            <div class="bar-container">
                                                <div class="bar-3"></div>
                                            </div>
                                        </div>
                                        <div class="side right">
                                            <div id="3a"></div>
                                        </div>
                                        <div class="side">
                                            <div>2 <span class="fa fa-star checked"></span></div>
                                        </div>
                                        <div class="middle">
                                            <div class="bar-container">
                                                <div class="bar-2"></div>
                                            </div>
                                        </div>
                                        <div class="side right">
                                            <div id="2a"></div>
                                        </div>
                                        <div class="side">
                                            <div>1 <span class="fa fa-star checked"></span></div>
                                        </div>
                                        <div class="middle">
                                            <div class="bar-container">
                                                <div class="bar-1"></div>
                                            </div>
                                        </div>
                                        <div class="side right">
                                            <div id="1a"></div>
                                        </div>
                                    </div>
                                </div>

                                <div id="cusRating">
                                    <h5>Đánh giá chất lượng tour</h5>

                                    <c:choose>
                                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                                            <form>
                                                <div class="row p-3" id="ratingPanel">
                                                    <input type="radio" id="5star" name="rate" value="5" hidden>
                                                    <label for="5star"><span class="fa fa-star"></label>
                                                    <input type="radio" id="4star" name="rate" value="4" hidden>
                                                    <label for="4star"><span class="fa fa-star"></label>
                                                    <input type="radio" id="3star" name="rate" value="3" hidden>
                                                    <label for="3star"><span class="fa fa-star"></label>
                                                    <input type="radio" id="2star" name="rate" value="2" hidden>
                                                    <label for="2star"><span class="fa fa-star"></label>
                                                    <input type="radio" id="1star" name="rate" value="1" hidden>
                                                    <label for="1star"><span class="fa fa-star"></label>
                                                </div>
                                            </form>
                                            <button id="confirmRatingBtn" class="btn btn-primary">Xác nhận đánh
                                                giá</button>
                                        </c:when>
                                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                                            <p>Bạn phải đăng nhập để có thể đánh giá tour du lịch này</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mod-content horizontal-border">
                <h3 class="mt-3">Bình luận khách hàng</h3>
                <div id="cusCommentInput" class="row m-2">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <textarea rows="2" cols="140" placeholder="Nội dung..." id="cmtContent"
                                class="col-12 mt-3"></textarea>
                            <button id="addComment" class="btn btn-primary mt-3">Thêm bình luận mới</button>
                        </c:when>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <p class="col-12 text-center text-danger">Bạn phải đăng nhập để có thể bình luận</p>
                        </c:when>
                    </c:choose>
                </div>
                <div class="block-comment">
                    <div id="cusCommentDetail" class="row p-4"></div>
                </div>
            </div>
        </div>
    </div>
</div>