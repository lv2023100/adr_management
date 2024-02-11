<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<security:authentication property="principal.member.name"
	var="memberName" />
<body>
	<div class="card mb-12 text-bg-dark"
		style="opacity: 0.7;">
		<div class="card-body">
			<h4>Hello ${memberName }!</h4>
			<h5>Welcome to ADR management.</h5>
			<h1 style="padding-top:20px">Introduce</h1>
			<hr>
			<p>
				架構決策記錄（<abbr title="Architectural Decision Record">ADR</abbr>）是一種文件，用於記錄軟體或系統架構中的重要決策。這些決策通常涉及到系統設計、技術選擇、架構模式、擴展性、性能、安全性等方面，它們對整個專案或系統的演進和維護都具有重要影響。
			</p>

			<h2 style="padding-top:30px">ADR 的主要目的</h2>
			<hr>
			<ul>
				<li>追蹤決策歷史：ADR 記錄了關鍵決策的歷史，幫助團隊和開發者了解為什麼選擇了某種特定方法或技術。</li>
				<li>提供文檔化支援：ADR 提供了架構決策的文件化支持，使新成員或項目參與者能夠理解並遵循先前的決策。</li>
				<li>討論和審查：ADR 也可以用作架構決策的討論和審查工具，確保決策是明智的，且經過充分的思考和評估。</li>
				<li>跟蹤演進：隨著專案的演進，ADR 可能需要更新或被替代，以反映新的需求或技術變化。</li>
			</ul>

			<h2 style="padding-top:30px">ADR 常常包括以下關鍵信息</h2>
			<hr>
			<ul>
				<li><strong>標題（Title）：</strong> ADR 的標題通常概括了所記錄的決策主題。</li>
				<li><strong>上下文（Context）：</strong> 詳細描述了為什麼需要做出這項決策，背景信息以及相關問題。</li>
				<li><strong>決策（Decision）：</strong> 詳細描述了最終的決策，包括選擇的方案、技術、架構等。</li>
				<li><strong>理由（Rationale）：</strong> 解釋了為什麼做出這項決策的原因，包括優點和缺點的評估。</li>
				<li><strong>後果（Consequences）：</strong> 討論了決策的潛在後果，包括可能的風險和影響。</li>
				<li><strong>狀態（Status）：</strong> 描述ADR的當前狀態，例如 "草稿"、"已完成" 等。</li>
				<li><strong>創建時間（Create Time）和更新時間（Update Time）：</strong>
					記錄了ADR的創建時間和最後更新時間。</li>
			</ul>
		</div>
	</div>
</body>