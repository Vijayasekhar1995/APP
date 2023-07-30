package com.sbms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbms.Entitys.FinalTicketResponse;

public interface TicketRepository extends JpaRepository<FinalTicketResponse, Integer> {

}
