package com.tbf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tbf.model.Listing;
import com.tbf.model.User;

public interface ListingRepository extends JpaRepository<Listing, Long>{
	
	List<Listing> findByStatusOrderByUploadTimeDesc(String status);
	List<Listing> findByBuyerIdAndStatusOrderByUploadTimeDesc(long buyerId, String status);
	List<Listing> findBySellerIdAndStatusOrderByUploadTimeDesc(long sellerId, String status );
}
