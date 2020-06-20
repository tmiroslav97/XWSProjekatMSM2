package agent.app.controller;

import agent.app.service.intf.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam(value = "photo", required = true) MultipartFile photo,
                                         @RequestParam(value = "data", required = true) String data
    ) throws IOException {
        System.out.println("-----------------------UPLOAD FILE---------------------");
        String name = imageService.uploadImage(photo);
        if(name != null){
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Slika nije dodata.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/load", method = RequestMethod.GET)
    public ResponseEntity<?> loadImage(@RequestParam(value = "ad_id", required = true) Long ad_id,
                                         @RequestParam(value = "name", required = true) String name
    ){
        System.out.println("METODAA ZA LOAD SLIKE SRC");
        return new ResponseEntity<>(imageService.findImageLocationByName(name,ad_id), HttpStatus.BAD_REQUEST);
    }

}
