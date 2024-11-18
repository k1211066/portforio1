package com.work.pac.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.pac.dto.WorkRequest;
import com.work.pac.dto.WorkUpdateRequest;
import com.work.pac.entity.Work;
import com.work.pac.service.WorkService;

import lombok.RequiredArgsConstructor;

@RequestMapping("")
@Controller
@RequiredArgsConstructor
public class WorkController {

	@Autowired
	private WorkService workService;

	  /**
	   * リスト画面を表示
	   * @param 画面表示モデル
	   * @return リスト画面
	   */
	@GetMapping("/list")
	  public String displayList(Model model) {
		  List<Work> worklist = workService.searchAll();
		  model.addAttribute("worklist", worklist);
		  return "list";
	  }

	/*
	   * 検索結果画面を表示
	   * @param 検索用キーワード＆モデル
	   * @return 検索結果画面
	   */
	  @GetMapping("/search")
	  public String search(@RequestParam String name, String state, String priority, Model model) {
		  List<Work> searchResult = workService.search(name, state, priority);
		  model.addAttribute("searchResult", searchResult);
		  return "search";
	  }

	  @GetMapping("/add")
	  public String displayadd(Model model) {
		  model.addAttribute("workRequest", new WorkRequest());
		  return "add";
	  }

	  @PostMapping("/work/create")
	  public String create(@Validated @ModelAttribute WorkRequest workRequest, BindingResult result, Model model) {
		  
		if (result.hasErrors()) {
		  List<String> errorList = new ArrayList<String>();
		  for (ObjectError error : result.getAllErrors()) {
		    errorList.add(error.getDefaultMessage());
		  }
		  model.addAttribute("validationError", errorList);
		  return "add";
		}
		workService.create(workRequest);
		return "redirect:/list";
	  }

	  /*
	   * 詳細画面表示
	   * @return 詳細画面
	   * 
	   */
	  @GetMapping("/work/{id}")
	  public String displayView(@PathVariable Long id, Model model) {
		  Work work = workService.findById(id);
		  model.addAttribute("workData", work);
		  return "work/view";
	  }

	  /*
	   * id指定後削除（物理削除）
	   * @return リスト画面
	   */
	  @GetMapping("/work/{id}/delete")
	  public String delete(@PathVariable Long id, Model model) {
		  workService.delete(id);
		  return "redirect:/work/list";
	  }

	  @GetMapping("/work/{id}/edit")
	  public String displayEdit(@PathVariable Long id, Model model) {
		  Work work = workService.findById(id);
		  WorkUpdateRequest workUpdateRequest = new WorkUpdateRequest();
		  workUpdateRequest.setId(work.getId());
		  workUpdateRequest.setName(work.getName());
		  workUpdateRequest.setState(work.getState());
		  workUpdateRequest.setDeadline(work.getDeadline());
		  workUpdateRequest.setPriority(work.getPriority());
		  workUpdateRequest.setDetail(work.getDetail());
		  model.addAttribute("workUpdateRequest", workUpdateRequest);
		  return "work/edit";
	  }
	  
	  @PostMapping("/work/update")
	  public String update(@Validated @ModelAttribute WorkUpdateRequest workUpdateRequest, BindingResult result, Model model) {

		  if (result.hasErrors()) {
			  List<String> errorList = new ArrayList<String>();

			  for (ObjectError error : result.getAllErrors()) {
				  errorList.add(error.getDefaultMessage());
			  }
			  model.addAttribute("validationError", errorList);
			  return "work/edit";
		  }

		  workService.update(workUpdateRequest);
		  return String.format("redirect:/work/%d",workUpdateRequest.getId());
		  
	  }

}
