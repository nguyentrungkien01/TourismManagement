
function getFeatureInfo(){
    fetch("/TourismManagement/quan-tri-vien/dac-diem-ngay-khoi-hanh/thong-tin")
        .then(res => {
            if (res.status != 200)
                return res.status
            return res.json()
        }).then(data => {
        if (data == 204) {
            alert("thông tin trống")
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

$(document).ready(function () {
    $('#loading').hide()
    getFeatureInfo()
    $('#departureDateCreatedBtn').click(function () {
        alert("Xác nhận tạo ngày khởi hành mới")
        $(this).hide()
        $('#loading').show()
    })
})