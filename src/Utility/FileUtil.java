/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Phương thức này mở một cửa sổ JFileChooser để người dùng chọn nơi lưu file.
 * Nếu file được chọn không có phần mở rộng tương ứng với tham số 'extension', 
 * phần mở rộng này sẽ được thêm vào cuối tên file.
 * 
 * @param extension Phần mở rộng mong muốn cho file được lưu.
 * @return File được chọn với phần mở rộng phù hợp. Trả về null nếu người dùng hủy bỏ hộp thoại.
 */
public class FileUtil {
    public static File getSaveFileWithExtension(String extension) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Xác định đường dẫn và tên tệp");
            
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            // Kiểm tra file có đuôi .xls không để thêm vào
            if (!fileToSave.getAbsolutePath().endsWith(".xls")){
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xls");
            }
            return fileToSave;
        }
        return null;
    }
}
