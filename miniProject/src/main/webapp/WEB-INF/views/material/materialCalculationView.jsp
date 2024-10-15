<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coding Bamboo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="d-flex justify-content-center mx-auto" style="width: 1340px;">
		<div class="d-flex justify-content-around" style="width: 100%">
			<div class="d-flex flex-column">
				<div class="d-flex flex-row">
					<div class="col-3">
						<label class="col-form-label" for="inputWord">검색어 입력</label>
					</div>
					<div class="ms-1 me-1 col-6">
						<input class="form-control" id="inputWord" type="text" onkeydown="checkEnterKey(event,'materialSearch')">
					</div>
					<div class="col-2">
						<button class="btn btn-primary" id="materialSearch" type="button">검색</button>
					</div>
				</div>
				<div class="table-responsive">
					<table>
						<thead>
							<th scope="col" style="width: 100px;"></th>
							<th scope="col" style="width: 150px;">자재이름</th>
							<th scope="col" style="width: 150px;">자재별 탄소배출량</th>
							<th scope="col" style="width: 60px;"></th>
						</thead>
					</table>
					<div class="overflow-y-scroll" style="height: 600px;">
						<table>
							<tbody id="searchedResult">
								<c:forEach items="${materialList}" var="meterial">
									<tr>
										<input type="hidden" value="${meterial.meNo}">
										<td scope="row" style="width: 100px;"></td>
										<td scope="row" style="width: 150px;">${meterial.meName}</td>
										<td class="text-center" scope="row" style="width: 150px;">${meterial.meEmission}</td>
										<td scope="row" style="width: 60px;"><button onclick="addItem()">추가</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div>
				<div class="d-flex flex-column item-aligns-center" style="height: 600px;">
					<span class="text-center">현재 목록</span>
					<table>
						<thead>
							<th scope="col" style="width: 300px;">자재이름</th>
							<th scope="col" style="width: 200px;">총 중량</th>
							<th scope="col" style="width: 150px;">총 탄소배출량</th>
							<th style="width: 60px;"></th>
						</thead>
						<tbody id="currentList">
						</tbody>
					</table>
				</div>
				<div>
					<div>
						<span>총 예상 탄소배출량 : </span> <span id="totalEmission">0</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>

	// 소수점 다섯째 자리까지 보이게 하기
	function showFiveDecimalPlaces(number) {
    	return number.toString().match(/^-?\d+(?:\.\d{0,5})?/)[0];
	}

	// 검색 URL
	const v_searchMaterial = "<c:url value='/materialSearch' />"
	
	// 검색 결과가 나타날 곳
	const v_searchedResult = document.getElementById("searchedResult")
	
	// 검색 input에서 enter key 활성화
	function checkEnterKey(event,buttonId){
		if(event.key == "Enter"){
			document.getElementById(buttonId).click()
		}
	}

	// 검색 행동
	document.getElementById("materialSearch").addEventListener("click",()=>{
		$.ajax({
			type:'POST',
			url: v_searchMaterial,
			data: { "word" : document.getElementById("inputWord").value },
			success: function(result){
				v_searchedResult.innerHTML = ""
				if(result.length == 0){
					v_searchedResult.innerHTML +=
						'<tr>' +
						'<td colspan="4">' +
						'<span>검색 결과가 없습니다</span>'
						'</td>' +
						'</tr>'
				}else{
					for(let i = 0; i < result.length; i++){
						v_searchedResult.innerHTML +=
							'<tr>' +
							'<input type="hidden" value="'+ result[i]["meNo"] +'">' +
							'<td scope="row" style="width:100px;"></td>' +
							'<td scope="row" style="width:150px;">'+ result[i]["meName"] + '</td>' +
							'<td class="text-center" scope="row" style="width:150px;">'+ result[i]["meEmission"] + '</td>' +
							'<td scope="row" style="width:60px;"><button onclick="addItem()">추가</button></td>' +
							'</tr>'
					}
				}
			}
		})
	})
	
	// 자재 항목에 있는 자재 목록 인덱스
	let v_material_list = []
	
	// 자재 불러오기 URL
	const v_getMaterial = "<c:url value='/getMaterial' />"
	
	// 자재 항목에 추가
	function addItem(){
		let item_index = event.target.parentElement.parentElement.children[0].value
		if(v_material_list.includes(item_index)){
			alert("해당 자재는 이미 항목에 있습니다.")
		}else{
			$.ajax({
				type:'POST',
				url: v_getMaterial,
				data: { "meNo" : item_index },
				success: function(result){
					
					// 자재 항목에 있는 자재 배열에 추가
					v_material_list.push(item_index)
					
					// 현재 테이블의 모든 input 요소의 값을 저장
				    const inputs = document.querySelectorAll("#currentList input[type='number']")
				    const values = Array.from(inputs).map(input => input.value)

				    // 테이블 업데이트 전 기존 값들을 초기화하지 않고 유지하기 위해 테이블을 재작성
				    let updatedInnerHTML = ''
				    const rows = document.querySelectorAll("#currentList tr");
				    
				    rows.forEach((row, index) => {
				        updatedInnerHTML += '<tr>' +
				        '<input type="hidden" value="' + row.querySelector("input[type='hidden']").value + '">' +
				        '<input type="hidden" value="' + row.querySelectorAll("input[type='hidden']")[1].value + '">' +
				        '<td scope="row">' + row.cells[0].innerText + '</td>' +
				        '<td scope="row"><input type="number" value="' + values[index] + '" onchange="calcEmission()" ></td>' +
				        '<td class="text-center class-for-summary" scope="row">' + row.cells[2].innerText + '</td>' +
				        '<td scope="row"><button onclick="deleteItem()">삭제</button></td>' +
				        '</tr>';
				    });

				    // 새로운 항목 추가
				    updatedInnerHTML +=
				        '<tr>' +
				        '<input type="hidden" value="' + result["meNo"] + '" >' +
				        '<input type="hidden" value="' + result["meEmission"] + '" >' +
				        '<td scope="row">' + result["meName"] + '</td>' +
				        '<td scope="row"><input type="number" onchange="calcEmission()" ></td>' +
				        '<td class="text-center class-for-summary" scope="row">' + 0 + '</td>' +
				        '<td scope="row"><button onclick="deleteItem()">삭제</button></td>' +
				        '</tr>'

				    // 테이블 업데이트
				    document.getElementById("currentList").innerHTML = updatedInnerHTML
				    console.log(v_material_list)
				}
			})
		}
	}
	
	// 자제 항목에서 제거
	function deleteItem(){
		const delete_index = event.target.parentElement.parentElement.children[0].value
		const get_index = v_material_list.indexOf(delete_index)
		v_material_list.splice(get_index,1)
		event.target.parentElement.parentElement.remove()
		calsTotalEmission()
		console.log(v_material_list)
	}
	
	// 자재의 탄소 배출량 계산
	function calcEmission(){
		let emission = event.target.parentElement.parentElement.children[1].value
		event.target.parentElement.parentElement.children[4].innerHTML = showFiveDecimalPlaces(event.target.value * emission)
		calsTotalEmission()
	}
	
	// 항목에 있는 자재들의 총 탄소 배출량 계산
	function calsTotalEmission(){
		v_allResult = document.getElementsByClassName("class-for-summary")
		
		let sum = 0;
		for(let i=0;i<v_allResult.length;i++){
			sum += parseFloat(v_allResult[i].innerHTML);
		}
		
		document.getElementById("totalEmission").innerHTML = showFiveDecimalPlaces(sum)
	}
</script>
</html>