document.addEventListener("DOMContentLoaded", () => {
  const modalEl = document.querySelector(".popup-wrap");
  const closeBtn = modalEl.querySelector(".confirm");

  const setModalEvent = () => {
    document.querySelectorAll(".safeTXNBoard").forEach(row => {
      row.addEventListener("click", function () {
        const safeNo = this.dataset.safeNo;
        console.log(`선택된 거래 번호: ${safeNo}`);

        // 리뷰 데이터 요청
        $.ajax({
          url: `${mypath}/admin/safeTXNBoardModal.do`,
          type: "post",
          data: { safeNo: safeNo },
          success: function (res) {
            console.log("모달 데이터:", res);

            // 모달 내용 업데이트
            $("#img_box img").attr({
              src: res.file_path,
              alt: `${res.prod_name} 이미지`,
            });
            $("#prod_name").text(res.prod_name);
            $("#prod_price").text(`${res.prod_price.toLocaleString()}원`);
            $("#mem_name").text(res.p.mem_no);
            $("#review_cont").text(res.t.mem_no);

            // 별점 생성
            

            // 모달 열기
            modalEl.style.display = "flex";
          },
          error: function (xhr) {
            alert("데이터 요청 실패: " + xhr.status);
          },
        });
      });
    });
  };

  // 모달 닫기 이벤트
  closeBtn.addEventListener("click", () => {
    modalEl.style.display = "none"; // 모달 숨기기
  });

  // 전역에서 호출 가능하도록 함수 노출
  window.setModalEvent = setModalEvent;
});
