//package io;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
////import java.util.Map;
//
//import model.Student;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
//
//public class JsonUtils {
//	String filePath = "sinh_vien.json";
//	private static final ObjectMapper mapper = new ObjectMapper();
//	public static void saveToJson(List<Student> listStd, String filePath) throws IOException {
//        mapper.writeValue(new File(filePath), listStd);
//        
//    }
//
//    public static List<Student> loadFromJson(String filePath) throws IOException {
//        return mapper.readValue(new File(filePath), new TypeReference<List<Student>>() {});
//        try {
//            // Lưu danh sách sinh viên vào file JSON
//            JsonUtils.saveToJson(sinh_vien, filePath);
//
//            // Đọc lại danh sách sinh viên từ file JSON
//            List<Student> loadedSinhVienList = JsonUtils.loadFromJson(filePath);
//
//            // Hiển thị thông tin sinh viên đã đọc
//            for (Student sv : loadedSinhVienList) {
//                System.out.println();
//                loadedSinhVienList.forEach(System.out::println);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }}
//        
//    }
//    
//
//    
//    
//
