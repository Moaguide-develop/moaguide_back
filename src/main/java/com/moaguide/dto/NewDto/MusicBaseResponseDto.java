package com.moaguide.dto.NewDto;

import com.moaguide.dto.NewDto.customDto.MusicDivideResponseDto;
import com.moaguide.dto.NewDto.customDto.MusicPublishDto;
import com.moaguide.dto.NewDto.customDto.MusicSongDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
// 기본정보
public class MusicBaseResponseDto {
    // 발행정보
    private MusicPublishDto musicPublish;
    // 곡 정보
    private MusicSongDto musicSong;
    // 저작권료 정보
    private MusicDivideResponseDto musicDivide;
}
