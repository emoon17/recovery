package com.example.wehago;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator {

    /**
     *
     * pakageName : 패키지 이름 작성
     * ex) pakageName : eh
     * = com.admin.eh
     *
     * entityName : 프리픽스 이름 작성
     * ex) entityName : eh
     * = com.admin.eh.EhController
     *
     *  이름 변경 후 해당 파일만 실행
     */
    private static String pakageName = "transcation"; // 패키지 경로 이름
    private static String entityName = "Transcation"; // 파일에 붙는 이름


    public static void main(String[] args) {


        String basePackage = "com.example.wehago." + pakageName;
        String baseDir = "./src/main/java/" + basePackage.replace(".", "/");


        try {
            // 디렉토리 구조 생성
            createDirectories(baseDir);

            // 파일 생성 및 내용 작성
            createFileWithContent(baseDir + "/controller/", entityName + "Controller.java", generateControllerCode(basePackage, entityName));
            createFileWithContent(baseDir + "/dto/", entityName + "RequestDto.java", generateRequestDtoCode(basePackage, entityName));
            createFileWithContent(baseDir + "/mapper/", entityName + "Mapper.java", generateMapperCode(basePackage, entityName));
            createFileWithContent("src/main/resources/mapper/", entityName + "Mapper.xml", generateMapperXmlCode(basePackage, entityName));
            createFileWithContent(baseDir + "/dto/", entityName + ".java", generateModelCode(basePackage, entityName));
            createFileWithContent(baseDir + "/service/", entityName + "Service.java", generateServiceInterfaceCode(basePackage, entityName));
            createFileWithContent(baseDir + "/service/", entityName + "ServiceImpl.java", generateServiceImplCode(basePackage, entityName));
            createFileWithContent(baseDir + "/dto/", entityName + "Condition.java", generateConditionCode(basePackage, entityName));

            System.out.println("파일 생성이 완료되었습니다!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 디렉토리 생성 메서드
    private static void createDirectories(String baseDir) throws IOException {
        String[] subDirs = {"/controller/","/dto/", "/mapper/", "/service/"};
        for (String subDir : subDirs) {
            File dir = new File(baseDir + subDir);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new IOException("디렉토리를 생성할 수 없습니다: " + dir.getPath());
                }
            }
        }
        File resourceMapperDir = new File("src/main/resources/mapper/");
        if (!resourceMapperDir.exists() && !resourceMapperDir.mkdirs()) {
            throw new IOException("resources/mapper 디렉토리 생성 실패");
        }
    }

    // 파일 생성 및 내용 작성 메서드
    private static void createFileWithContent(String path, String fileName, String content) throws IOException {
        File file = new File(path + fileName);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(content);
            }
        }
    }

    // Controller 템플릿 코드
    private static String generateControllerCode(String basePackage, String entityName) {
        return "package " + basePackage + ".controller;\n\n" +
                "import " + basePackage + ".service." + entityName + "Service;\n" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import org.springframework.web.bind.annotation.*;\n\n" +
                "@RestController\n" +
                "@RequiredArgsConstructor\n" +
                "@RequestMapping(\"/api/" + entityName.toLowerCase() + "\")\n" +
                "public class " + entityName + "Controller {\n\n" +
                "    private final " + entityName + "Service " + lowerCaseFirstLetter(entityName) + "Service;\n\n" +
                "}";
    }

    // Request DTO 템플릿 코드
    private static String generateRequestDtoCode(String basePackage, String entityName) {
        return "package " + basePackage + ".dto.request;\n\n" +
                "import lombok.Data;\n\n" +
                "@Data\n" +
                "public class " + entityName + "RequestDto {\n" +
                "    private String name;\n" +
                "    private String description;\n" +
                "}";
    }


    // Mapper 인터페이스 템플릿 코드
    private static String generateMapperCode(String basePackage, String entityName) {
        return "package " + basePackage + ".mapper;\n\n" +
                "import org.apache.ibatis.annotations.*;\n\n" +
                "@Mapper\n" +
                "public interface " + entityName + "Mapper {\n\n" +
                "}";
    }

    // Mapper XML 템플릿 코드
    private static String generateMapperXmlCode(String basePackage, String entityName) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n" +
                "<mapper namespace=\"" + basePackage + ".mapper." + entityName + "Mapper\">\n\n" +
                "</mapper>";
    }

    // Model 템플릿 코드
    private static String generateModelCode(String basePackage, String entityName) {
        return "package " + basePackage + ".dto;\n\n" +
                "import lombok.Data;\n\n" +
                "@Data\n" +
                "public class " + entityName + " {\n" +
                "    private Long id;\n" +
                "    private String name;\n" +
                "    private String description;\n" +
                "}";
    }

    // Service 인터페이스 템플릿 코드
    private static String generateServiceInterfaceCode(String basePackage, String entityName) {
        return "package " + basePackage + ".service;\n\n" +
                "public interface " + entityName + "Service {\n" +
                "}";
    }

    // ServiceImpl 템플릿 코드 생성
    private static String generateServiceImplCode(String basePackage, String entityName) {
        String lowerEntityName = lowerCaseFirstLetter(entityName);

        return "package " + basePackage + ".service;\n\n" +
                "import " + basePackage + ".mapper." + entityName + "Mapper;\n" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n\n" +
                "@Transactional\n" +
                "@Service\n" +
                "@RequiredArgsConstructor\n" +
                "public class " + entityName + "ServiceImpl implements " + entityName + "Service {\n\n" +
                "    private final " + entityName + "Mapper " + lowerEntityName + "Mapper;\n\n" +
                "}";
    }

    // 소문자로 첫 글자를 변환하는 메서드
    private static String lowerCaseFirstLetter(String input) {
        return Character.toLowerCase(input.charAt(0)) + input.substring(1);
    }

    private static String generateConditionCode(String basePackage, String entityName) {
        return "package " + basePackage + ".dto;\n\n" +
                "import lombok.Data;\n\n" +
                "/**\n" +
                " * " + entityName + "Condition 클래스는 조회 조건을 정의합니다.\n" +
                " */\n\n" +
                "@Data\n" +
                "public class " + entityName + "Condition {\n\n" +
                "    private String name;\n" +
                "    private String description;\n" +
                "}";
    }

}


