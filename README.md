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
## Mọi người clone về máy mình nhé !
# Đây là phiên bản từ điển Command line cải tiến lần 2,
#mọi người xem và cho ý kiến nhé !
## Các chức năng cũ:
1. Hàm insertFromCommandline() có chức năng nhập liệu. 
2. Hàm showAllWords() có chức năng hiển thị kết quả danh sách dữ liệu từ điển trên màn hình.
3. Hàm dictionaryBasic() có chức năng gọi hàm insertFromCommandline() và showAllWords().
## Các chức năng mới(so với bản sơ khai):
1. Bổ sung hàm insertFromFile() có chức năng nhập dữ liệu từ điển từ tệp dictionaries.txt .
2. Bổ sung hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
3. Bổ sung hàm dictionaryAdvanced() có chức năng gọi các hàm insertFromFile(), showAllWords() và dictionaryLookup() .

4. Bổ sung hàm insertFromFile() có chức năng nhập dữ liệu từ điển từ tệp dictionaries.txt .
5. Bổ sung hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
6. Bổ sung hàm dictionaryAdvanced() có chức năng gọi các hàm insertFromFile(), showAllWords() và dictionaryLookup() .
## Các chức năng mới :
7. Class DictionaryManagement phát triển thêm các hàm có chức năng thêm , sửa , xóa dữ liệu bằng dòng lệnh .
8. Class DictionaryCommandLine bổ sung hàm dictionarySearcher() có chức năng tìm kiếm các từ .
9. Class DictionaryManagement bổ sung hàm dictionaryExportToFile() có chức năng xuất dữ liệu từ điển hiện tại ra files .

![ezgif-1-8fcd2b6237e3](https://user-images.githubusercontent.com/80667158/138657769-f4fb4dca-2547-4c1c-8141-28757ba1a4c1.gif)


