function filter() {
    if (window.self === window.top) {
        window.top.onload
        window.location.href = "http://localhost/fsm_system/admin/login.php";
    }
}

