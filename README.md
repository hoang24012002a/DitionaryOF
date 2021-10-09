##Cập nhật giao diện lần 2
* Chỉnh sửa lại layout pane search.(Tuấn gửi bản thô, Trường hoàn thiện)
* cấu trúc lại thư mục.
* Done(nhưng chưa đẹp)
* Thêm lib JFoenix(hỗ trợ đồ hoạ 2D) https://github.com/sshahine/JFoenix
* Các layout có style.css riêng
* Các file css được thêm thẳng vào .fxml nên các file .java ko cần câu lệnh scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

##Hạn chế
* Vẫn chưa hoàn thiện được phần đồ hoạ
* Font chưa vẫn xấu (do lười chưa nhiều label quá :))
* Các button vẫn thô
* Chưa có hiệu ứng chuyển scene
* Ý tưởng về Account vẫn chưa đi vào đâu

## Cập nhật giao diện lần 1
pane search cơ bản là hoàn thiện
cần xây dựng method chuyển pane để việc xây dựng các pane như Account, Setting, About
!!! Ý tưởng mới(đang hoàn thiện)
{
mỗi người dùng sẽ có một account
setting sẽ có thể điều chỉnh tốc độ hay giọng(nam/nữ)
about sẽ nêu danh các tác giả cùng với link để tải phần mềm
(dự định là sẽ đóng cả mã nguồn thành 1 file jar để có thể tiện cho việc chia sẻ)
nhưng khá là khó bởi conflig môi trường của từng máy...
}
## Hiệu ứng (đang hoàn thiện trên file css)
## Hạn chế
Font chữ còn thô kệch
Các button chưa tạo được hiệu ứng press, hover
Chưa hoàn thiện xong các pane Acc, Set,...(cố gắng hoàn thiện trong tuần).

