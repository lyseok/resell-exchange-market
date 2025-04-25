const headTitleEl = $('.header-head-title');
const popupBodyEl = $('.header-popup-body');
const popupFootEl = $('.header-popup-foot');


if(loginState !== 'null'){
  const dipositStarterEl = document.querySelector('.diposit-starter');
  const dipositEl = document.querySelector('.diposit');
  const ulEl = $('.diposit ul');
  const notificationSubEl = document.querySelector('.notification');
  const balanceEl = $('#balance span');

  dipositStarterEl.addEventListener('click', function (event) {
    event.stopPropagation();
    
    if(dipositEl.classList.contains('show')){
      hideDiposit();

    }else{
      reloadDiposit();

      showDiposit();
      notificationSubEl.classList.remove('show');
    }
  });
  dipositEl.addEventListener('click', function(event) {
    event.stopPropagation();
  });

  window.addEventListener('click', function () {
    hideDiposit();
  });

  function showDiposit() {
    headTitleEl.html('띹페이 충전하기기');
    popupBodyEl.html(/*html*/`
      <p>
        충전하실 금액을 입력해주세요.(원)
      </p>
      <input type="text" name="ex_price" id="ex_price" placeholder="금액입력">
    `);
    popupFootEl.html(/*html*/`
      <span class="header-pop-btn confirm" id="payment">확인</span>
      <span class="header-pop-btn close" id="header-close">취소</span>
    `);


    dipositEl.classList.add('show');
  }

  function hideDiposit() {
    dipositEl.classList.remove('show');
  }

  const reloadDiposit = () => {
    $.ajax({
      url:`${mypath}/selectAllDeposit.do`,
      type: 'get',
      data: {
        no: headerInfo.mem_no
      },
      success: function(data){
		console.log(data);
        code='';
  
        $.each(data, function(i, v){
          code += /* html */`
            <li>
              <span>
                <span>
                  <h3>${v.tfr_cont.substr(3, 5)}</h3>
                  <p>${v.tfr_create_at}</p>
                </span>
                <b>${v.tran_type == 0 ? '거래' : v.tran_type == 1 ? '충전' : '출금'}</b>
                <h3>${v.price}원</h3>
              </span>
            </li>            
          `;
        });
  		if(data.length > 0){
        	balanceEl.html(data[0].mem_bal);			
		} else {
        	balanceEl.html(headerInfo.mem_bal);						
		}
        ulEl.html(code);
      },
      error: function(xhr){
        console.log(xhr.status);
      },
      dataType: 'json'
    });
  }
  
  
  IMP.init("imp82653811");

  $(document).on('click', '#payment', function() {
    modalClose();
    if($('#ex_price').val() != null && $('#ex_price').val() != ''){
      onClickPay();
    }
  });

  const onClickPay = () => {
    const today = new Date();
    const hours = today.getHours();
    const minutes = today.getMinutes();
    const seconds = today.getSeconds();
    const milliseconds = today.getMilliseconds();
    const makeMerchantUid = `${hours}${minutes}${seconds}${milliseconds}`;

    IMP.request_pay({
      pg: 'kakaopay.TC0ONETIME',
      pay_method: 'kakaopay',
      name: '띹페이충전',
      amount: $('#ex_price').val(),
      merchant_uid: "IMP" + makeMerchantUid,
      buyer_email: headerInfo.mem_email,
      buyer_name: headerInfo.mem_no
    }, function(res) {
      console.log(res);
      
      if (res.success) {
        $.ajax({
          url: `${mypath}/insertDeposit.do`,
          type: 'POST',
          data: {
            mem_no: res.buyer_name,
            price: res.paid_amount,
            cont: res.name
          },
          success: function(data) {
            console.log(data);
            if (data > 0) {
              headTitleEl.html('띹페이');
              popupBodyEl.html(/*html*/`
                <p>
                  충전이 성공적으로<br>완료되었습니다.
                </p>
              `);
              popupFootEl.html(/*html*/`
                <span class="header-pop-btn confirm" id="payment">확인</span>
              `);
                openHeaderModal();

              // alert('결제 완료!');
              reloadDiposit();
            } else {
              headTitleEl.html('띹페이');
              popupBodyEl.html(/*html*/`
                <p>
                  충전을 실패했습니다<br>관리자에게 문의바랍니다.
                </p>
              `);
              popupFootEl.html(/*html*/`
                <span class="header-pop-btn confirm" id="payment">확인</span>
              `);
                openHeaderModal();
              //alert('결제 실패! 관리자에게 문의바랍니다.');
            }
          },
          error: function(xhr) {
            console.log(xhr.status);
          },
          dataType: 'json'
        });

      } else {
        alert(res.error_msg);
      }
    });
  };
  
  
}


const dipositView = data =>{
  $.ajax({
    url:`${mypath}/`,
    type: 'get',
    data: {
      notif_id: data.notif_id,
      mem_id: data.mem_id
    },
    success: function(data){
      console.log(data);
    },
    error: function(xhr){
      console.log(xhr.status);
    },
    dataType: 'json'
  });
  location.href = `${mypath}${data.notif_url}`;
}


// $("#payment").click(function(){
//   modalClose();
//   //컨펌 이벤트 처리
// });

$("#header-modal-open").click(function(){       
  openHeaderModal();
});

const openHeaderModal = () => {
  $("#header-popup").css('display','flex').hide().fadeIn();
}

$(document).on('click', '#header-close', function() {
  modalClose();
})

function modalClose(){
  $("#header-popup").fadeOut();
}