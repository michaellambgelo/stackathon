package dev.michaellamb.redis.stackathon.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;

import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.AccessLevel;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
public class Person {
  // Id Field, also indexed
  @Id
  @Indexed
  private String id;

  // Indexed for exact text matching
  @Indexed @NonNull
  private String firstName;

  @Indexed @NonNull
  private String lastName;

  // Indexed for exact text matches
  @Indexed @NonNull
  private String discordTag;

  // Indexed for numeric matches
  @Indexed @NonNull
  private Integer discordDiscriminator;

  @Indexed @NonNull
  private Set<String> submissionLinkSet;
}
