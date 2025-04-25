/**
 * 
 */



const memInfo = (data) => {
  return /*html*/ `
  <tr>
    <td class="td_mbid">${data.mem_no}</td>
    <td class="td_mbname">${data.mem_name}</td>
    <td class="td_mbname sv_use">${data.mem_alias}</td>
    <td class="td_num">${data.mem_tel}</td>
    <td>${data.mem_bal}</td>
    <td class="td_boolean">${data.mem_status == 0 ? '일반회원' : data.mem_status == 1 ? '탈퇴회원' : '제제회원'}</td>
    <td class="td_boolean">${data.mem_join_at}</td>
  </tr>
  `;
}

const prodInfo = (data) => {
  return /*html*/ `
  <tr>
    <td class="td_category">${data.prod_no}</td>
    <td class="td_category">${data.mem_no}</td>
    <td class="td_num">${data.prod_name}</td>
    <td>${data.prod_price}</td>
    <td class="td_boolean">${data.prod_tr_status == 0 ? '판매중' : data.prod_tr_status == 1 ? '판매완료' : '예약중'}</td>
    <td class="td_boolean">${data.prod_deleted == 0 ? 'x' : 'o'}</td>
    <td class="td_datetime">${data.prod_at}</td>
  </tr>
  `;
}

const transInfo = (data) => {
  return /*html*/ `
  <tr>
    <td class="td_boolean">${data.txn_no}</td>
    <td class="td_category">${data.seller}</td>
    <td class="td_category">${data.buyer}</td>
    <td class="td_num">${data.prod_name}</td>
    <td>${data.prod_price}</td>
    <td class="td_boolean">거래완료</td>
    <td class="td_datetime">${data.txn_create_at}</td>
  </tr>
  `;
}

const memListView =() => {
  code = '';
  $.each(memList, function(i, v){
    code += memInfo(v);
  });
  $('#memTbody').html(code);
}

const prodListView =() => {
  code = '';
  $.each(prodList, function(i, v){
    code += prodInfo(v);
  });
  $('#prodTbody').html(code);
}

const transListView =() => {
  code = '';
  $.each(transList, function(i, v){
    code += transInfo(v);
  });
  $('#transTbody').html(code);
}

memListView();
prodListView();
transListView();