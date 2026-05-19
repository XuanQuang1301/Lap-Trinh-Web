### ĐỀ THI THỰC HÀNH LẬP TRÌNH WEB 
**Chủ đề: Xây dựng ứng dụng Quản lý Mượn/Trả Sách**

**Câu 1. Tạo CSDL gồm 1 bảng có cấu trúc như sau và nhập một số dữ liệu mẫu (1.0đ):**
Bảng `Book` (Sách)

| STT | Tên trường | Kiểu dữ liệu | Ý nghĩa |
| :--- | :--- | :--- | :--- |
| 1 | id | TEXT | Mã sách (Khóa chính) |
| 2 | title | TEXT | Tên sách |
| 3 | author | TEXT | Tác giả |
| 4 | category | TEXT | Thể loại |
| 5 | status | INT | Trạng thái (0: Có sẵn trên kệ, 1: Đã cho mượn) |

**Câu 2. Xây dựng ứng dụng quản lý Sách với các tính năng sau (9.0đ):**

* **Tính năng Hiển thị & Lọc dữ liệu (3.0đ):**
    * Giao diện ban đầu (`index.html`) gồm 2 phần:
        * Phía trên là một Form tìm kiếm/lọc Sách theo **Thể loại** (chỉ cần 1 ô nhập text `category` và 1 nút **Lọc**).
        * Phía dưới hiển thị **toàn bộ danh sách Sách** dưới dạng bảng. Nếu người dùng nhập Thể loại và bấm Lọc, bảng chỉ hiển thị các sách thuộc thể loại đó (tìm kiếm gần đúng hoặc chính xác đều được).
        * Dưới cùng có một link/nút **"Thêm sách mới"**.

* **Tính năng Session (1.0đ):**
    * Dùng session để lưu trữ thông tin về `category` (Thể loại) vừa tìm kiếm. Khi người dùng load lại trang web hoặc đi từ trang khác về trang chủ, ô tìm kiếm và bảng dữ liệu phải tự động hiển thị kết quả của thể loại đã lọc trước đó.

* **Tính năng Mượn / Trả sách (2.0đ):**
    * Tại mỗi hàng của bảng danh sách, cột cuối cùng (Hành động) xử lý như sau:
        * Nếu sách đang có sẵn (`status = 0`): Hiển thị nút **"Mượn sách"** màu xanh.
        * Nếu sách đã cho mượn (`status = 1`): Hiển thị nút **"Trả sách"** màu đỏ.
    * Khi bấm nút **"Mượn sách"**: Cập nhật `status` trong CSDL thành `1` và load lại trang chủ (nút biến thành màu đỏ).
    * Khi bấm nút **"Trả sách"**: Cập nhật `status` trong CSDL về `0` và load lại trang chủ (nút biến lại thành màu xanh).

* **Tính năng Thêm Sách mới (3.0đ):**
    * Khi bấm vào link **"Thêm sách mới"**, chuyển sang trang `add.html` có Form nhập thông tin: Mã sách, Tên sách, Tác giả, Thể loại (dùng thẻ select với các tùy chọn: *IT, Văn học, Kinh tế, Khác*).
    * Khi Submit form, thực hiện validate:
        * Các ô không được để trống.
        * Mã sách không được trùng lặp.
        * *(Nếu có lỗi: Báo lỗi màu đỏ ngay cạnh ô nhập bị sai và giữ nguyên dữ liệu đã gõ).*
    * Nếu dữ liệu hợp lệ: Lưu sách vào CSDL (với `status` mặc định là `0`), sau đó tự động chuyển hướng (`redirect`) về thẳng trang chủ (`index.html`) để xem sách vừa thêm.