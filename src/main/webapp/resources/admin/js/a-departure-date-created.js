function getFeatureInfo() {
    fetch("/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
            if (data == 204) {
                Swal.fire({
                    title: 'Thông báo !',
                    text: "Thông tin trống!",
                    icon: 'warning',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Ok'
                })
                return
            }
            let options = '';
            for (let i = 0; i < data.length; i++) {
                options += `
                     <option value="${data[i]['feaSlug']}">${data[i]['feaName']}</option>
                `
            }
            document.getElementById('feaSlug').innerHTML = options
        })

}

function validateCreatedDepartureDate() {
    let dptDate = $('#dptDate').val()

    if (dptDate == '') {
        Swal.fire({
            title: 'Thông báo !',
            text: "Vui lòng kiểm tra lại thông tin còn thiếu trước khi tạo!",
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok'
        })
        return false
    }
    return true
}

$(document).ready(function () {
    // validate
    $("#departureDateCreatedForm").validate({
        rules: {
            dptDate: {
                required: true,
                date: true
            },
        },
        messages: {
            dptDate: {
                required: "Ngày khởi hành không được để trống",
                data: "Ngày khởi hành không được để trống",
            },
        }
    });
    $('#loading').hide()
    getFeatureInfo()
    $('#departureDateCreatedBtn').click(function () {
        if (validateCreatedDepartureDate()) {
            Swal.fire({
                title: 'Thông báo !',
                text: "Tạo thành công",
                icon: 'success',
                confirmButtonColor: '#3085d6'
            })
            $(this).hide()
            $('#loading').show()
        }
    })
})