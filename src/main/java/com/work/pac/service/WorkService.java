package com.work.pac.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.pac.dto.WorkRequest;
import com.work.pac.dto.WorkUpdateRequest;
import com.work.pac.entity.Work;
import com.work.pac.repository.WorkRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkService {

	/*
	 * レポジトリ
	 */
	@Autowired
	private WorkRepository workRepository;
	
	/*
	 * 全リスト取得
	 * @return 全検索 
	 * 
	 */
	public List<Work> searchAll(){
		return workRepository.findAll();
	}
	
	/*
	 * リスト検索（''の場合trueとする）
	 * @return 検索結果
	 *  
	 */
	public List<Work> search(String name, String state, String priority) {
		if ( state == "") {
			return workRepository.findByNameContainingAndStateContainingAndPriorityContaining(name,state, priority);
		}
		return workRepository.findByNameContainingAndStateEqualsAndPriorityContaining(name,state, priority);
	}
	
	public void create(WorkRequest workRequest) {
	    Date now = new Date();
	    Work work = new Work();
	    work.setName(workRequest.getName());
	    work.setState(workRequest.getState());
	    work.setDeadline(workRequest.getDeadline());
	    work.setPriority(workRequest.getPriority());
	    work.setDetail(workRequest.getDetail());
	    work.setCreateDate(now);
	    work.setUpdateDate(now);
	    workRepository.save(work);

	}

	/*
	 *	id検索 
	 *	@return 検索結果
	 * 
	 */
	public Work findById(Long id) {
		return workRepository.findById(id).get();
	}
	
	/*
	 * id指定後、削除（物理削除）
	 * 
	 */
	public void delete(Long id) {
		Work work = findById(id);
		workRepository.delete(work);
	}


	  /**
	   * ユーザー情報 更新
	   *  work ユーザー情報
	   */
	  public void update(WorkUpdateRequest workUpdateRequest) {
		Work work = findById(workUpdateRequest.getId());
		work.setName(workUpdateRequest.getName());
		work.setState(workUpdateRequest.getState());
		work.setDeadline(workUpdateRequest.getDeadline());
		work.setPriority(workUpdateRequest.getPriority());
		work.setDetail(workUpdateRequest.getDetail());
		work.setUpdateDate(new Date());
		workRepository.save(work);
	  }

}
