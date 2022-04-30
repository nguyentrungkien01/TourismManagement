<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="card-header pb-0 px-3">
        <h5 class="mb-0">Thêm dịch vụ</h5>
    </div>
    <div class="card-body pt-4 p-3">
        <form action="<c:url value='/quan-tri-vien/dich-vu' />" method="post">
            <div class="form-group">
                <label for="servTitle">Tiêu đề</label>
                <input type="text" class="form-control" id="servTitle" name="servTitle" placeholder="Nhập tiêu đề...">
            </div>
            <div class="form-group">
                <label for="servContent">Nội dung</label>
                <input type="text" class="form-control" id="servContent" name="servContent"
                    placeholder="Nhập nội dung...">
            </div>
            <button id="serviceCreatedBtn" type="submit" class="btn btn-primary">Tạo mới</button>
        </form>
    </div>
</div>