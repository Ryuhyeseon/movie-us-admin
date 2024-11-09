package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.dto.ReviewResponseDTO;
import com.ucamp.movieusAdmin.entity.Movie;
import com.ucamp.movieusAdmin.entity.ReviewEntity;
import com.ucamp.movieusAdmin.repository.MovieRepository;
import com.ucamp.movieusAdmin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    public List<ReviewResponseDTO> getReviewList() {
        Sort sort = Sort.by(Sort.Direction.ASC, "reviewId");
        List<ReviewEntity> reviews = reviewRepository.findAll(sort);
        return reviews.stream()
                .map(this::convertToResponseDTO) // 변환 메서드 호출
                .collect(Collectors.toList());
    }

    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }


    private final String API_KEY = "40405429a36ddf7b1d4337a022992fbc";
    private final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private ReviewResponseDTO convertToResponseDTO(ReviewEntity review) {
        Movie movie = movieRepository.findByTmdbId(review.getTmdbId())
                .orElse(null);  // 없으면 null 반환
        ReviewResponseDTO responseDTO = modelMapper.map(review, ReviewResponseDTO.class);

        if (movie!=null) {
            responseDTO.setTitle(movie.getTitle());
        } else {
            System.out.println("movie=null");
                try {
                    Map<String, Object> movieDetails = getMovieDetailsFromApi(review.getTmdbId());
                    responseDTO.setTitle((String) movieDetails.get("title"));
                    Map<String, Object> collection = (Map<String, Object>) movieDetails.get("belongs_to_collection");
                    if (collection != null) {
                        responseDTO.setTitle((String) collection.get("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        return responseDTO;
    }

    private Map<String, Object> getMovieDetailsFromApi(Long tmdbId) {
        String url = BASE_URL + tmdbId + "?api_key=" + API_KEY + "&language=ko-KR";
        return restTemplate.getForObject(url, Map.class);
    }

}
