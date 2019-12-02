package st.example.boot.parser.rest;

import lombok.AllArgsConstructor;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import st.example.boot.parser.service.FileParser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class FileUploadAndParseController {

    private FileParser fileParser;
    private List<String> fileNames = new ArrayList<>();

    @GetMapping("/startUploadFileFromCP")
    public String startParse() throws Exception {
        fileParser.parse();
        return "done";
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", fileNames);

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/commonsFileUpload",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String getViaCommonsFileUploadStreamingApi(HttpServletRequest request) throws Exception {
        ServletFileUpload upload = new ServletFileUpload();
        FileItemIterator iter = upload.getItemIterator(request);
        fileParser.parseInSeparateThread(iter);
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {
        fileParser.parse3(file);
        redirectAttributes.addFlashAttribute("message",
                "Done " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

}
