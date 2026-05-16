package org.example.th3muanha.control;


import jakarta.servlet.http.HttpSession;
import org.example.th3muanha.entity.House;
import org.example.th3muanha.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class HouseController {
    @Autowired
    private HouseRepository houseRepository;
    @GetMapping("/")
    public String showSearchForm(){
        return "search";
    }
    @PostMapping("/search")
    public String searchHouse(@RequestParam String id,
                              @RequestParam String address,
                              @RequestParam String areaStr,
                              @RequestParam String type,
                              HttpSession session,
                              Model model){
        Integer area = null;
        if(!areaStr.trim().isEmpty()){
            try {
                area = Integer.parseInt(areaStr);
            }catch(NumberFormatException e){
                area = null;
            }
        }
        session.setAttribute("searchId", id);
        session.setAttribute("searchAddress", address);
        session.setAttribute("searchArea", area);
        session.setAttribute("searchType", type);
        List<House> result = houseRepository.searchHouseByAtLeastOnField(id, address, area, type);
        model.addAttribute("houses", result);
        return "results";
    }
    @PostMapping("/buy")
    public String buyHouse(@RequestParam String id){
        House house = houseRepository.findById(id).orElse(null);
        if(house != null){
            house.setSold(1);
            houseRepository.save(house);
        }
        return "redirect:/";
    }
}
