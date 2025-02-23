package com.moaguide.service;

import com.moaguide.domain.Announcement.AnnouncementRepository;
import com.moaguide.dto.NewDto.customDto.AnnouncementDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public Page<AnnouncementDto> getNotice(Pageable pageable) {

        return announcementRepository.findAllNotice(pageable);
    }

    public AnnouncementDto getNoticeDetail(Long id) {
        return announcementRepository.findNoticeDetail(id);
    }
}
