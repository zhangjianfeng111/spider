selectUserList
===
SELECT
	user_id,
	heed_image_url,
	nickname,
	user_name
FROM
	USER_INFO
WHERE
	user_id > 74523	and status = 0