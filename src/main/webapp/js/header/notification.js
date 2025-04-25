if(loginState !== 'null'){
  const notificationStarterEl = document.querySelector('.notification-starter');
  const notificationEl = document.querySelector('.notification');
  const ulEl = $('.notification ul');
  const dipositSubEl = document.querySelector('.diposit');
  
  notificationStarterEl.addEventListener('click', function (event) {
    event.stopPropagation();

    if(notificationEl.classList.contains('show')){
      hideNotification();

    }else{
      $.ajax({
        url:`${mypath}/selectAllNotification.do`,
        type: 'get',
        data: {
          no: headerInfo.mem_no
        },
        success: function(data){
          code = '';
          $.each(data, function(i, v){
            code += /*html*/`
              <li><a href="${mypath}/viewNotification.do?mem_no=${v.mem_no}&notif_id=${v.notif_id}&url=${v.notif_url}">${v.notif_message}</a></li>
            `;
          });
          if(code === '') {
            code = /*html*/`<li><a href="javascript:void(0)">알림이 존재하지 않습니다.</a></li>`;
          }
          ulEl.html(code);
        },
        error: function(xhr){
          console.log(xhr.status);
        },
        dataType: 'json'
      })
      showNotification();
	  dipositSubEl.classList.remove('show');
    }
  });
  notificationEl.addEventListener('click', function(event) {
    event.stopPropagation();
  });

  window.addEventListener('click', function () {
    hideNotification();
  });

  function showNotification() {
    notificationEl.classList.add('show');
  }

  function hideNotification() {
    notificationEl.classList.remove('show');
  }

}
const notificationView = data =>{
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


