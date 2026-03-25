using GymApp.Application.Interfaces;
using GymApp.Domain.Interfaces;
using GymApp.Domain.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace GymApp.API.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class MembershipsController : ControllerBase
    {
        private readonly IMembershipService _membershipService;

        public MembershipsController(IMembershipService membershipService)
        {
            _membershipService = membershipService;
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var result = await _membershipService.GetAllMembershipsAsync();
            return Ok(result);
        }

        [HttpGet("{id:int}")]
        public async Task<IActionResult> GetById(int id)
        {
            var result = await _membershipService.GetMembershipByIdAsync(id);
            return result is null ? NotFound() : Ok(result);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] Membership membership)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            membership.IssueDate = DateTime.SpecifyKind(membership.IssueDate, DateTimeKind.Utc);
            membership.ExpirationDate = DateTime.SpecifyKind(membership.ExpirationDate, DateTimeKind.Utc);
            try
            {
                await _membershipService.CreateMembershipAsync(membership);

                return CreatedAtAction(
                    nameof(GetById),
                    new { id = membership.Id },
                    membership);
            }
            catch (ArgumentException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpPut("{id:int}")]
        public async Task<IActionResult> Update(int id, [FromBody] Membership membership)
        {
            if (id != membership.Id)
                return BadRequest("Route id does not match body id.");

            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            membership.IssueDate = DateTime.SpecifyKind(membership.IssueDate, DateTimeKind.Utc);
            membership.ExpirationDate = DateTime.SpecifyKind(membership.ExpirationDate, DateTimeKind.Utc);
            try
            {
                await _membershipService.UpdateMembershipAsync(membership);
                return NoContent();
            }
            catch (ArgumentException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("{id:int}")]
        public async Task<IActionResult> Delete(int id)
        {
            try
            {
                await _membershipService.DeleteMembershipAsync(id);
                return NoContent();
            }
            catch (Exception)
            {
                return StatusCode(500);
            }
        }
    }
}
