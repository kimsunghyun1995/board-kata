package com.kata.board.post.service

import com.kata.board.post.domain.DateUtils
import com.kata.board.post.domain.Post
import com.kata.board.post.domain.PostCommandRepository
import com.kata.board.post.domain.PostReadRepository
import com.kata.board.post.service.request.PostRequest
import com.kata.board.post.service.response.PagingResponse
import com.kata.board.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postReadRepository: PostReadRepository,
    private val postCommandRepository: PostCommandRepository,
    private val userService: UserService
) {
    fun findAllPagenatedPost(page: Pageable): Page<PagingResponse> {
        val pagenatedPost = postReadRepository.findAllPagenatedPost(page)
        return pagenatedPost.map { post ->
            PagingResponse(
                post.id,
                post.title,
                post.content,
                post.user?.username,
                post.view,
                DateUtils.convertToLocalDate(post.createdDate)
            )
        }
    }

    @Transactional
    fun registerPost(request: PostRequest) {
        val user = userService.findUser(request.userId!!)
        postCommandRepository.save(Post(request.title!!, request.content!!, user))
    }
}