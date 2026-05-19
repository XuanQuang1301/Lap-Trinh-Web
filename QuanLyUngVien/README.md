### ĐỀ THI THỰC HÀNH LẬP TRÌNH WEB
**Chủ đề: Xây dựng ứng dụng quản lý Hồ sơ Ứng viên**

**Câu 1. Tạo CSDL gồm 1 bảng có cấu trúc như sau và nhập 1 số dữ liệu mẫu (1.0đ):**
Bảng `Candidate` (Ứng viên)

| STT | Tên trường | Kiểu dữ liệu |
| :--- | :--- | :--- |
| 1 | id | TEXT |
| 2 | name | TEXT |
| 3 | position | TEXT |
| 4 | exp_year | INT |
| 5 | status | INT |

**Câu 2. Xây dựng ứng dụng quản lý Ứng viên với các tính năng sau (9.0đ):**

* **Tính năng Nhập liệu (4.0đ):**
    * Giao diện ban đầu là 1 trang HTML (`index.html`) có Form gồm 4 ô nhập liệu cho các trường 1-4. Trong đó, ô `position` là hộp chọn thả xuống (Select) với các lựa chọn: *Frontend, Backend, Mobile, Tester*. Cuối form có nút **Submit** và 1 link **"Xem danh sách chờ"**.
    * Khi bấm nút **Submit**, thực hiện validate:
        * Không ô nào được để trống.
        * Mã ứng viên (`id`) không được trùng với dữ liệu đã có trong CSDL.
        * *Nếu có lỗi:* Quay lại Form nhập và hiển thị dòng chữ báo lỗi màu đỏ ngay cạnh ô bị lỗi.
    * Nếu dữ liệu hợp lệ: Chuyển sang một trang Xác nhận (`confirm.html`) hiển thị lại thông tin vừa nhập, kèm 2 nút **Confirm** và **Back**.
    * Khi bấm **Confirm**, lưu ứng viên vào CSDL (trường `status` lưu giá trị mặc định là 0), sau đó quay về trang giao diện ban đầu.

* **Tính năng Session (1.0đ):**
    * Dùng session để lưu trữ thông tin về `position` (Vị trí ứng tuyển) vừa chọn. Khi bị lỗi validate phải quay lại form, hoặc khi lưu thành công quay lại form, hệ thống phải tự động chọn lại đúng vị trí đó trong hộp thả xuống.

* **Tính năng Duyệt hồ sơ (4.0đ):**
    * Khi bấm vào link **"Xem danh sách chờ"** ở trang chủ, hệ thống lấy từ CSDL ra tất cả ứng viên có trường `status = 0` và hiển thị trên một trang mới (`list.html`) dưới dạng bảng.
    * Tại mỗi hàng của bảng, cột cuối cùng (Hành động) chứa 2 nút: **Pass** và **Fail**.
    * Nếu người dùng bấm nút **Pass**: Cập nhật trường `status` của ứng viên đó trong CSDL thành `1`, sau đó tải lại trang danh sách (lúc này ứng viên đó sẽ biến mất khỏi bảng vì status không còn là 0 nữa).
    * Nếu người dùng bấm nút **Fail**: Xóa hẳn ứng viên đó khỏi CSDL, sau đó tải lại trang danh sách.