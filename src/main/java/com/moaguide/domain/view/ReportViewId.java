package com.moaguide.domain.view;

import com.moaguide.domain.report.Report;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReportViewId {
    @Column(name="Local_key")
    private String localKey;

    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "report_Id", referencedColumnName = "id", nullable = false)
    private Report reportId;
}
