<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="row">
    <div class="col-12">
        <div class="card mb-4">
            <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                <h5>Quản lý vai trò</h5>
                <a href="/TourismManagement/quan-tri-vien/vai-tro/tao-moi" class="badge badge-success p-2 text-capitalize mb-2">Thêm vai
                    trò</a>
            </div>
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mã vai
                                    trò
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    Tên vai trò</th>
                                <th
                                    class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    Slug</th>
                                <th
                                    class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    Tuỳ chỉnh</th>
                            </tr>
                        </thead>
                        <tbody id="roleInfo">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>