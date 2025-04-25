

const promotionEl = document.querySelector('.promotion');
const promotionToggleBtn = document.querySelector('.toggle-promotion');
let isHidePromotion = true;

promotionToggleBtn.addEventListener('click', function() {
	isHidePromotion = !isHidePromotion;
	if (isHidePromotion){
		promotionEl.classList.add('hide');
	}else{
		promotionEl.classList.remove('hide');
	}
});

$(".select_wrap").mouseenter(function(){
	$(".select_box").fadeIn();
})
$(".select_wrap").mouseleave(function(){
	$(".select_box").fadeOut();
})

