package S3Vision.demo;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AppController {
    S3 amazonS3Client;

    @RequestMapping("/")
    public String homepage(Model model)
    {
        amazonS3Client = new S3();
        List<String> bucketlist = amazonS3Client.getBucketList();
        model.addAttribute("bucketlist", bucketlist);

        amazonS3Client.loadFileToBucket("buckettest1", "123");
        List<String> bucketListFiles = amazonS3Client.getBucketListFromFile("buckettest1");
        System.out.println(bucketListFiles);
        model.addAttribute("bucketlistFiles", bucketListFiles);

        String response = new Vision().detector(new File ("pug.jpg"));
        model.addAttribute("response", response);
        System.out.println(response);

        return "/home";
    }
}
