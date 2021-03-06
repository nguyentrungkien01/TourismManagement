let currentPageIndex = 1;

function getTourInfo(pageIndex = null, kw = null) {
    let path = '/TourismManagement/nhan-vien/dat-tour/thong-tin'
    if (kw != null)
        path += `?kw=${kw}`
    if (pageIndex != null)
        path += `${path.includes('?') ? '&' : '?'}trang=${pageIndex}`
    fetch(path).then(res => {
        if (res.status != 200)
            return res.status
        return res.json()
    }).then(data => {
        let rows = ''
        for (let i = 0; i < data.length; i++) {
            let saleFromDate = data[i]['saleFromDate'] == null ? "Không có giảm giá" : new Date(data[i]['saleFromDate'])
                .toISOString().split('T')[0]
            let saleToDate = data[i]['saleToDate'] == null ? "Không có giảm giá" : new Date(data[i]['saleToDate'])
                .toISOString().split('T')[0]

            rows += `
                 <tr>
                    <td class="text-center">
                        <a href="/TourismManagement/nhan-vien/dat-tour/${data[i]['tourSlug']}" 
                        class="badge badge-success text-capitalize">Xem chi tiết</a>
                    </td>
                    <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['tourId']}</span>
                    </td>
                      <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['tourTitle']}</span>
                    </td>
                      <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['tourSlug']}</span>
                    </td>
                      <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['catName']}</span>
                    </td>
                      <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${saleFromDate}</span>
                    </td>
                     <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${saleToDate}</span>
                    </td>
                     <td class="align-middle text-center">
                        <span class="text-secondary text-xs font-weight-bold">${data[i]['tourAverageRating']}</span>
                    </td>
                     <td class="align-middle text-center">
                        <div> 
                          <img src="${data[i]['tourCoverPage']}" class="avatar avatar-sm me-3" alt="Tour cover page"> 
                        </div>
                    </td>
                </tr>
            `
        }
        $('#tourInfo').html(rows)
    })
}

function getPageAmount(kw=null) {
    let path = '/TourismManagement/nhan-vien/dat-tour/so-trang'
    if (kw !== null)
        path += `?kw=${kw}`
    fetch(path)
        .then(res => res.json()).then(data => {
        let pageAmount = data['pageAmount']
        let rows = ''
        for (let i = 1; i <= pageAmount; i++)
            rows += `
                 <li class="page-item" onclick="changePage(${i}, ${pageAmount})"><a class="page-link" href="javascript:;">${i}</a></li>
            `
        if (pageAmount > 1) {
            let preBtn = ` <li class="page-item" onclick="getPreviousPage(${pageAmount})" id="preBtn">
                                <a class="page-link" href="javascript:;">Trước</a></li>`
            let nextBtn = ` <li class="page-item" onclick="getNextPage(${pageAmount})" id="nextBtn">
                                <a class="page-link" href="javascript:;">Sau</a></li>`
            rows = preBtn + rows
            rows += nextBtn
        }
        $('#pagination').html(pageAmount !== 1 ? rows : '')
        if (pageAmount !== 1)
            $(`#pagination li:nth-child(2)`).addClass('active')
        if (currentPageIndex === 1)
            $('#preBtn').hide()
    })
}

function getPreviousPage(pageAmount) {
    $('#search').val('')

    if (currentPageIndex > 1) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex--;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getTourInfo(currentPageIndex)
    }
    if (currentPageIndex == 1)
        $('#preBtn').hide()
    if (currentPageIndex != 1)
        $('#preBtn').show()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
}

function changePage(pageIndex, pageAmount) {

    $('#search').val('')
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
    currentPageIndex = pageIndex
    $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
    getTourInfo(currentPageIndex)

    if (pageIndex == 1) {
        $('#preBtn').hide()
        $('#nextBtn').show()
    }
    if (pageIndex == pageAmount) {
        $('#nextBtn').hide()
        $('#preBtn').show()
    }

    if (pageIndex != 1 && pageIndex != pageAmount) {
        $('#preBtn').show()
        $('#nextBtn').show()
    }
}

function getNextPage(pageAmount) {
    $('#search').val('')
    if (currentPageIndex < pageAmount) {
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).removeClass('active')
        currentPageIndex++;
        $(`#pagination li:nth-child(${currentPageIndex + 1})`).addClass('active')
        getTourInfo(currentPageIndex)
    }
    if (currentPageIndex == pageAmount)
        $('#nextBtn').hide()
    if (currentPageIndex != pageAmount)
        $('#nextBtn').show()
    if (currentPageIndex != 1)
        $('#preBtn').show()
}
$(document).ready(function () {
    getTourInfo(currentPageIndex)
    getPageAmount()
    $('#search').keyup(function () {
        currentPageIndex=1
        getTourInfo(currentPageIndex, $(this).val().length > 0 ? $(this).val().trim() : null)
        getPageAmount($(this).val().length > 0 ? $(this).val().trim() : null)
    })

})