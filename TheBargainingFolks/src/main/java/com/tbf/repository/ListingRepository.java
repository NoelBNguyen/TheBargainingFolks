package com.tbf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tbf.model.Listing;
import com.tbf.model.User;

public interface ListingRepository extends JpaRepository<Listing, Long>{
	
	List<Listing> findByStatusOrderByUploadTimeAsc(String status);
	List<Listing> findByBuyerIdAndStatusOrderByUploadTimeAsc(long buyerId, String status);
	List<Listing> findBySellerIdAndStatusOrderByUploadTimeAsc(long sellerId, String status );
}
