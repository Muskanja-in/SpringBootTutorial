package com.example.SubmissionForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class FormController {

    @Autowired
    CustomersRepo repo;
    @RequestMapping("")
    public String details()
    {
        return "muskan";
    }
    @PostMapping("details")
    public String details(Customers customers)
    {
        repo.save(customers);
        return "muskan";
    }
    @RequestMapping("getdetails")
    public String getdetails()
    {
        return "ViewCustomers";
    }

   @PostMapping("/getdetails")
   public ModelAndView getdetails(@RequestParam("cid") int cid)
   {
       ModelAndView mv = new ModelAndView("Retrieve");
       Customers customers = repo.findById(cid).orElse(null);
       mv.addObject(customers);
       return mv;
   }
   @RequestMapping("customers")
   @ResponseBody
    public List<Customers> getcustomers()
   {
       return repo.findAll();
   }
    @RequestMapping("customers/{cid}")
    @ResponseBody
    public Optional<Customers> getcustomers2(@PathVariable("cid") int cid)
    {
        return repo.findById(cid);
    }
    @PostMapping("customers")
    @ResponseBody
    public Customers getcustomers3(@RequestBody Customers customers)
    {
        repo.save(customers);
        return customers;
    }
    @DeleteMapping("customers/{cid}")
    public Customers getcustomers4(@PathVariable("cid") int cid)
    {
        Customers cust = repo.getById(cid);
        repo.delete(cust);
        return cust;
    }
    @PutMapping(path ="/customers", consumes={"application/json"})
    public Customers getcustomers5(@RequestBody Customers customers)
    {
        repo.save(customers);
        return customers;
    }
}
