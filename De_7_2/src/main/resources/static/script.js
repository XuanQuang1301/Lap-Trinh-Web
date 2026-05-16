let currentStudent = {};
window.onload =() => {
    const savedDept = sessionStorage.getItem("lastDepartment");
        if (savedDept) {
            document.getElementById("studentDept").value = savedDept;
        }
};
async function handleAdd(){
    const id = document.getElementById("studentId").value.trim();
    const name = document.getElementById("studentName").value.trim();
    const dob = document.getElementById("studentDob").value;
    const dept = document.getElementById("studentDept").value;
    const errorDiv = document.getElementById("form-error");
    errorDiv.innerText ="";
    if(!id || !name || !dob || !dept){
        errorDiv.innerText = "Nhap day du thong tin";
        return;
    }
    currentStudent = {id: id, name: name, dob: dob, department: dept};
    try{
        const response = await fetch('/api/students/validate', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(currentStudent)
        });
        if(response.ok){
            document.getElementById("conf-id").innerText = currentStudent.id;
            document.getElementById("conf-name").innerText = currentStudent.name;
            document.getElementById("conf-dob").innerText = currentStudent.dob;
            document.getElementById("conf-dept").innerText = currentStudent.department;

            // Chuyển màn hình: Ẩn form nhập, Hiện form xác nhận
            document.getElementById("form-section").style.display = "none";
            document.getElementById("confirm-section").style.display = "block";
        }else{
            const errorText = await response.text();
            errorDiv.innerText = errorText;
        }
    }
    catch{
        errorDiv.innerText = "Loi csdl"
    }
}
async function handleConfirm() {
    try {
        // Bắn cục dữ liệu sang Backend để lưu vào CSDL
        const response = await fetch('/api/students/confirm', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(currentStudent)
        });

        if (response.ok) {
            // Lưu Khoa vào Session để lần sau dùng
            sessionStorage.setItem("lastDepartment", currentStudent.department);

            // Xóa trắng form nhập
            handleReset();

            // Chuyển màn hình về lại form nhập
            document.getElementById("form-section").style.display = "block";
            document.getElementById("confirm-section").style.display = "none";

            // Báo thành công
            alert("Thêm sinh viên thành công!");
        } else {
            alert("Có lỗi xảy ra khi lưu vào CSDL!");
        }
    } catch (error) {
        alert("Lỗi kết nối tới Server!");
    }
}

// 5. Xử lý khi bấm nút "Back"
function handleBack() {
    // Ẩn form xác nhận, hiện lại form nhập
    document.getElementById("form-section").style.display = "block";
    document.getElementById("confirm-section").style.display = "none";
}

// 6. Xử lý khi bấm nút "Reset"
function handleReset() {
    // Xóa trắng các ô input
    document.getElementById("studentId").value = "";
    document.getElementById("studentName").value = "";
    document.getElementById("studentDob").value = "";

    // Riêng Khoa thì lấy lại từ Session đắp vào
    const savedDept = sessionStorage.getItem("lastDepartment");
    document.getElementById("studentDept").value = savedDept ? savedDept : "";

    // Xóa lỗi
    document.getElementById("form-error").innerText = "";
}