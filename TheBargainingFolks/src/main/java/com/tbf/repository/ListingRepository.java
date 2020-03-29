package com.tbf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbf.model.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long>{

}
