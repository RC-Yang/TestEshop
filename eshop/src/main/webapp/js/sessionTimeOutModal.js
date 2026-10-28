// js檔內可放html
const modalHtml = `
  <div class="modal fade" id="sessionWarningModal" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">連線即將過期</h5>
        </div>
        <div class="modal-body">
          您已長時間未操作，系統即將於5分鐘後自動登出。
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" id="btnLogoutNow">立即登出</button>
          <button type="button" class="btn btn-primary" id="btnExtendSession">延長連線</button>
        </div>
      </div>
    </div>
  </div>
`;


document.addEventListener('DOMContentLoaded', () => {
	// 0. 將 HTML 動態插入到頁面的 <body> 底部
  	document.body.insertAdjacentHTML('beforeend', modalHtml);
  
  	// 1. 初始化 Bootstrap Modal 實例
    const modalElement = document.getElementById('sessionWarningModal');
    const sessionModal = new bootstrap.Modal(modalElement);//這是把dom元素轉換成js物件

    // 紀錄最後一次操作/請求的時間
    let lastActivityTime = Date.now();
    let isModalShowing = false; // 避免重複觸發跳窗

    // 2. 每 10 秒檢查一次時間差
    const checkInterval = setInterval(() => {
      const elapsedMinutes = (Date.now() - lastActivityTime) / 1000 / 60;

      // 經過 25 分鐘且彈窗尚未顯示時，跳出提醒
      if (elapsedMinutes >= 1 && !isModalShowing) {
        isModalShowing = true;
        sessionModal.show(); // 顯示 Bootstrap Modal
      }
      
      // 如果超過 30 分鐘都沒有處理，強制轉址到登出頁
      if (elapsedMinutes >= 2) {
        clearInterval(checkInterval);
        window.location.href = '/eshop/home/logout';
      }
    }, 10000);
	
	    // 3. 點擊「延長連線」按鈕
	    document.getElementById('btnExtendSession').addEventListener('click', () => {
	      // 發送 AJAX ping 續約伺服器端 Session
	      fetch('/eshop/home/test/keepAlive', {
	        method: 'GET',
	        headers: { 'X-Requested-With': 'XMLHttpRequest' }
	      })
	      .then(response => {
	        if (response.ok) {//只要後端正常回傳資料，fetch api就必然會有response.ok
	          // 更新最後活動時間，並隱藏彈窗
	          lastActivityTime = Date.now();
	          isModalShowing = false;
	          sessionModal.hide();
	        } else {
	          window.location.href = '/eshop/home/logout';
	        }
	      })
	      .catch(() => {
	        window.location.href = '/eshop/home/logout';
	      });
	});

	// 4. 點擊「立即登出」按鈕
	  document.getElementById('btnLogoutNow').addEventListener('click', () => {
	    window.location.href = '/eshop/home/logout';
	  });
});