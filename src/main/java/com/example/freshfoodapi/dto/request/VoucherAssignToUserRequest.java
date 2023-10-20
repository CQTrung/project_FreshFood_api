package com.example.freshfoodapi.dto.request;

import com.example.freshfoodapi.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@SuperBuilder
public class VoucherAssignToUserRequest extends PageDto {
   private List<Long> userId;
   private Long voucherId;
}
