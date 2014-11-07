<%@ include file="../template/headerWithNav.jsp" %>
<style scoped>

</style>

<form>
  Select your favorite fruit:
  <select id="mySelect" value="orange">
    <option value="apple">Apple</option>
    <option value="orange">Orange</option>
    <option value="pineapple">Pineapple</option>
    <option value="banana">Banana</option>
  </select>
</form>

<script>
  $("#mySelect").val("banana");
</script>
<%@ include file="../template/footer.jsp" %>
