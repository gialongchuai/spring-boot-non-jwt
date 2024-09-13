package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customException.FieldException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.CustomerDTO;
import com.javaweb.model.UserDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;

@RestController
public class BuildingAPI {
	@Autowired
	private UserService userService;

	@GetMapping(value="/api/user")
	public List<UserDTO> getAllUser() {
		List<UserDTO> userDTOs = userService.findAll();
		return userDTOs;
	}
	
//	@GetMapping(value = "/api/building/")
//	public Object building(@RequestParam(value = "tenToaNha") String ten,
//			@RequestParam(value = "diaChiToaNha") String diaChi, @RequestParam(value = "soTang") String soTang) {
//
//		try {
//			System.out.println(5 / 0);
//		} catch (Exception e) {
//			// TODO: handle exception
//			ErrorResponse listError = new ErrorResponse();
//			listError.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("Co loi chia cho 0.");
//			details.add("\nLam on sua loi!");
//			listError.setDetails(details);
//			return listError;
//		}
//		List<BuildingDTO> result = new ArrayList<>();
//		BuildingDTO building1 = new BuildingDTO();
//		building1.setTenToaNha("ABC");
//		building1.setDiaChiToaNha("Hai Phong");
//		building1.setSoTang(5);
//		result.add(building1);
//		BuildingDTO building2 = new BuildingDTO();
//		building2.setTenToaNha("XYZ");
//		building2.setDiaChiToaNha("Ca Mau");
//		building2.setSoTang(10);
//		result.add(building2);
//		return result;
//	}

	@PostMapping(value = "/api/buildingsss/")
	public Object postBuilding(@RequestBody BuildingDTO buildingDTO) {
//		System.out.println(buildingDTO.getTenToaNha());
//		System.out.println(buildingDTO.getDiaChiToaNha());
//		System.out.println(buildingDTO.getSoTang());
		System.out.println(5 / 0);
//		try {
//			check(buildingDTO);
//		} catch (FieldException e) {
//			ErrorResponse err = new ErrorResponse();
//			err.setError(e.getMessage());
//			List<String> listDetail = new ArrayList<>();
//			listDetail.add(e.getMessage());
//			err.setDetails(listDetail);
//			return err;
//		}
//		BuildingDTO buildingDTOcopy = new BuildingDTO();
//		buildingDTOcopy = buildingDTO;
//		return buildingDTOcopy;
		return null;
	}

	@Autowired
	private CustomerService customerService;

	//@GetMapping(value = "api/customer/")
	public List<CustomerDTO> getCustomer(@RequestParam(value = "name") String name) {
		List<CustomerDTO> customerDTOs = customerService.findAll(name);
		return customerDTOs;
	}

	public void check(String s) throws FieldException {
		if (s == null || s.equals("")) {
			throw new FieldException("Co loi khong duoc de empty!");
		}
	}

	@DeleteMapping(value = "/api/building/{soDuong}/{soNha}")
	public void deleteBuilding(@PathVariable Integer soDuong, @PathVariable String soNha,
			@RequestParam(value = "toaNha") String tenToaNha) {
		System.out
				.println("Tai so duong: " + soDuong + " co so nha: " + soNha + " va co toan nha ten la: " + tenToaNha);
	}

	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params, @RequestParam(value="typecode")List<String> typecode) {
		List<BuildingDTO> buildingDTOs = buildingService.findAll(params, typecode);
		return buildingDTOs;
	};
}
