
$(function() {
    $(".btn-login").click(
        function() {
            $(".login_popup").fadeIn(), "easeOutExpo";
            $(".overlay").fadeIn(), "easeOutExpo";
            return false;

        }
    )
    $(".overlay").click(
        function() {
            $(".login_popup").fadeOut(), "easeOutExpo";
            $(".login_table").delay(400).animate({
                left: 0
            }, 600, "easeOutExpo");
            $(".forget_table").delay(400).animate({
                left: 400
            }, 600, "easeOutExpo");
            return false;

        }
    )
    $(".glyphicon-remove").click(
        function() {
            $(".login_popup").fadeOut(), "easeOutExpo";
            $(".login_table").delay(400).animate({
                left: 0
            }, 600, "easeOutExpo");
            $(".forget_table").delay(400).animate({
                left: 400
            }, 600, "easeOutExpo");

            return false;

        }
    )

})

