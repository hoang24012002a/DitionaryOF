##Cập nhật giao diện lần 2
* Chỉnh sửa lại layout pane search.
* cấu trúc lại thư mục.
## Cập nhật giao diện
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
# Đây là phiên bản từ điển Command line cải tiến lần 1,
#mọi người xem và cho ý kiến nhé !
## Các chức năng cũ:
1. Hàm insertFromCommandline() có chức năng nhập liệu. 
2. Hàm showAllWords() có chức năng hiển thị kết quả danh sách dữ liệu từ điển trên màn hình.
3. Hàm dictionaryBasic() có chức năng gọi hàm insertFromCommandline() và showAllWords().
## Các chức năng mới(so với bản sơ khai):
1. Bổ sung hàm insertFromFile() có chức năng nhập dữ liệu từ điển từ tệp dictionaries.txt .
2. Bổ sung hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
3. Bổ sung hàm dictionaryAdvanced() có chức năng gọi các hàm insertFromFile(), showAllWords() và dictionaryLookup() .

